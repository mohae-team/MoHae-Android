package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.usecase.DisLikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.LikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.PostPlaceInfoUseCase
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isValueBlank
import com.mohaeyo.mohae.mapper.PlaceMapper
import com.mohaeyo.mohae.model.MapMakerModel
import com.mohaeyo.mohae.model.PlaceModel
import io.reactivex.subscribers.DisposableSubscriber

class PlaceDocViewModel(
    private val getPlaceInfoUseCase: GetPlaceInfoUseCase,
    private val postPlaceInfoUseCase: PostPlaceInfoUseCase,
    private val placeMapper: PlaceMapper
): BaseLocationViewModel() {

    val placeName = MutableLiveData<String>()
    val placeDescription = MutableLiveData<String>()
    val placeLocation = MutableLiveData<String>()

    val placeNameErrorEvent = SingleLiveEvent<String>()
    val placeDescriptionErrorEvent = SingleLiveEvent<String>()

    val mapPlaceModelToEntity: (PlaceModel) -> PlaceEntity
            = { placeMapper.mapModelToEntity(it) }

    val mapPlaceEntityToModel: (PlaceEntity) -> PlaceModel
            = { placeMapper.mapFrom(it) }

    override fun updateAddressData(location: LatLng, addressTitle: String, addressSnippet: String, isSuccess: Boolean) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(title = addressTitle, snippet = addressSnippet, location = location)
            placeLocation.value = addressSnippet
            getPlaceInfo(addressSnippet)
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            placeLocation.value = "다른 지역을 선택해주세요."
        }
    }

    val startDocToListEvent = SingleLiveEvent<Unit>()

    private fun getPlaceInfo(location: String)
            = getPlaceInfoUseCase.execute(location, object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
        override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
            if (t.second.isSuccess) getSuccess(mapPlaceEntityToModel(t.first))
            else getFail(t.second.message)
        }

        override fun onComplete() {

        }

        override fun onError(t: Throwable?){
            createToastEvent.value = "알 수 없는 오류가 발생했습니다"
        }
    })

    private fun getSuccess(place: PlaceModel) {
        placeName.value = place.name
    }

    private fun getFail(message: String) {
        placeName.value = ""
        placeDescription.value = ""
        createToastEvent.value = message
    }

    private fun postPlaceInfo(place: PlaceModel)
            = postPlaceInfoUseCase.execute(mapPlaceModelToEntity(place), object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
        override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
            if (t.second.isSuccess) postSuccess(mapPlaceEntityToModel(t.first))
            else postFail(t.second.message)
        }

        override fun onComplete() {

        }

        override fun onError(t: Throwable?){
            createToastEvent.value = "알 수 없는 오류가 발생했습니다"
        }
    })

    private fun postSuccess(place: PlaceModel) {
        placeName.value = place.name
        placeDescription.value = place.description

        startDocToListEvent.call()
    }

    private fun postFail(message: String) {
        createToastEvent.value = message
    }

    fun clickPostPlace() =
        when {
            placeName.value.isNullOrBlank() -> placeNameErrorEvent.value = "이름을 정해주세요"
            placeDescription.value.isNullOrBlank() -> placeDescriptionErrorEvent.value = "설명을 적어주세요"
            else -> postPlaceInfo(PlaceModel(
                name = placeName.value!!,
                location = placeLocation.value!!,
                description = placeDescription.value!!,
                likeCount = 0,
                isLike = false
            ))
        }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}