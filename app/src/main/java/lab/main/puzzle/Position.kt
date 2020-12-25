package lab.main.puzzle

import android.view.View

class Position(val view: View, val number: Int) {
	val initialLocation = IntArray(2)
	private val thread = Thread(Runnable { initializeLocation() })

	init {
		thread.start()
	}

	private fun initializeLocation() {
		while(initialLocation[0] == 0 || initialLocation[1] == 0) {
			Thread.sleep(100)
			view.getLocationOnScreen(initialLocation)
		}
	}
}