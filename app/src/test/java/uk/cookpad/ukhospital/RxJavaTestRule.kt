package uk.cookpad.ukhospital

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Overloads RxJava 2 and RxAndroid Schedulers for unit tests
 */
class RxJavaTestRule : TestRule {

    override fun apply(base: Statement, description: Description) = object : Statement() {

        override fun evaluate() {
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setSingleSchedulerHandler { Schedulers.trampoline() }

            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }

            base.evaluate()

            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }
}
