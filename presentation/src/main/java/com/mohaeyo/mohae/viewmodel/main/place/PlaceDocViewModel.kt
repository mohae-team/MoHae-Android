package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.PostPlaceInfoUseCase
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.PlaceMapper
import com.mohaeyo.mohae.model.MapMakerModel
import com.mohaeyo.mohae.model.PlaceModel
import io.reactivex.subscribers.DisposableSubscriber

class PlaceDocViewModel(
    private val getPlaceInfoUseCase: GetPlaceInfoUseCase,
    private val postPlaceInfoUseCase: PostPlaceInfoUseCase,
    private val placeMapper: PlaceMapper
): BaseLocationViewModel() {
    val placeModel = MutableLiveData<PlaceModel>().apply {
        value = PlaceModel()
    }

    val placeNameErrorEvent = SingleLiveEvent<String>()
    val placeDescriptionErrorEvent = SingleLiveEvent<String>()
    val startDocToListEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    override fun updateAddressData(location: LatLng, addressTitle: String, addressSnippet: String, isSuccess: Boolean) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(title = addressTitle, snippet = addressSnippet, location = location)
            placeModel.value!!.location = addressSnippet
            getPlaceInfo(addressSnippet)
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            placeModel.value!!.location = ""
        }
    }

    fun clickPostPlace()
            = checkDocText()


    fun clickDocToList() {
        startDocToListEvent.call()
    }

    private fun getPlaceInfo(location: String)
            = getPlaceInfoUseCase.execute(location, object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
        override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
            if (t.second.isSuccess) getSuccess(placeMapper.mapFrom(t.first))
            else getFail(t.second.message)
        }

        override fun onComplete() {

        }

        override fun onError(t: Throwable?){
            createToastEvent.value = "알 수 없는 오류가 발생했습니다"
        }
    })

    fun getSuccess(place: PlaceModel) {
        placeModel.value = PlaceModel(
            name = place.name,
            location = place.location
        )
    }

    fun getFail(message: String) {
        placeModel.value = PlaceModel(
            name = "",
            location = placeModel.value!!.location
        )

        createToastEvent.value = message
    }

    private fun postPlaceInfo(place: PlaceModel)
            = postPlaceInfoUseCase.execute(placeMapper.mapModelToEntity(place), object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
        override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
            if (t.second.isSuccess) postSuccess(placeMapper.mapFrom(t.first))
            else postFail(t.second.message)
        }

        override fun onComplete() {

        }

        override fun onError(t: Throwable?){
            createToastEvent.value = "알 수 없는 오류가 발생했습니다"
        }
    })

    fun postSuccess(place: PlaceModel) {
        placeModel.value = PlaceModel(
            name = place.name,
            description = place.description
        )

        startDocToListEvent.call()
    }

    fun postFail(message: String) {
        createToastEvent.value = message
    }

    private fun checkDocText() {
        with(placeModel.value!!) {
            when {
                name.isBlank() ->
                    placeNameErrorEvent.value = "이름을 정해주세요"
                description.isBlank() ->
                    placeDescriptionErrorEvent.value = "설명을 적어주세요"
                else ->
                    postPlaceInfo(this)
            }
        }
    }
}