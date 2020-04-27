package uk.cookpad.ukhospital.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import uk.cookpad.ukhospital.data.HospitalDetailRepository
import uk.cookpad.ukhospital.util.SingleLiveData

class HomeViewModel(
    private val hospitalDetailRepository: HospitalDetailRepository
) : ViewModel() {

    private val _uiSate = SingleLiveData<UiState>()
    val uiSate: LiveData<UiState> = _uiSate

    private val _filter = MutableLiveData<String?>()
    val filter: LiveData<String?> = _filter

    val hospitalList = Transformations.switchMap(filter) { query ->
        hospitalDetailRepository.getHospitalListPaging(query).toLiveData(PAGE_SIZE)
    }

    private val compositeDisposable = CompositeDisposable()

    init {
        _filter.value = null
    }

    fun refreshHospitalData() {
        _uiSate.setValue(UiState.Loading)
        val disposable = hospitalDetailRepository.getHospitalData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ insertedRows ->
                _uiSate.setValue(UiState.Success(insertedRows))
            }, { error ->
                _uiSate.setValue(UiState.Error(error))
            })
        compositeDisposable.add(disposable)
    }

    fun setFilter(filterData: String?) {
        _filter.value = filterData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}

sealed class UiState {
    data class Success(val insertedRows: Int) : UiState()
    object Loading : UiState()
    data class Error(val error: Throwable, val isLocalDataAvailable: Boolean = false) : UiState()
}