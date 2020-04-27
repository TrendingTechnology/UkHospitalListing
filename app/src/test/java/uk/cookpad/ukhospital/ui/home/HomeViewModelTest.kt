package uk.cookpad.ukhospital.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.flextrade.kfixture.KFixture
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import uk.cookpad.ukhospital.RxJavaTestRule
import uk.cookpad.ukhospital.data.HospitalDetailRepository

class HomeViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val rxJavaTestRule = RxJavaTestRule()
    private lateinit var fixture: KFixture
    private lateinit var viewModel: HomeViewModel
    @Mock
    lateinit var mockHospitalDetailRepository: HospitalDetailRepository
    private val mockEvents = mock<Observer<UiState>>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fixture = KFixture()
        viewModel = HomeViewModel(hospitalDetailRepository = mockHospitalDetailRepository)
        viewModel.uiSate.observeForever(mockEvents)
    }

    @Test
    fun `When getHospitalData is success Then ViewState Loading and Success should be called`() {
        whenever(mockHospitalDetailRepository.getHospitalData()).thenReturn(Flowable.just(fixture()))

        // when
        viewModel.refreshHospitalData()

        // then
        verify(mockEvents).onChanged(
            any<UiState.Loading>()
        )
        verify(mockEvents).onChanged(
            any<UiState.Success>()
        )
    }

    @Test
    fun `When getHospitalData is failure Then ViewState Loading and Error should be called`() {
        whenever(mockHospitalDetailRepository.getHospitalData()).thenReturn(Flowable.error(Throwable()))

        // when
        viewModel.refreshHospitalData()

        // then
        verify(mockEvents).onChanged(
            any<UiState.Loading>()
        )
        verify(mockEvents).onChanged(
            any<UiState.Error>()
        )
    }
}