package lab.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lab.main.puzzle.PuzzleFragment

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val puzzleFragment = PuzzleFragment()

		supportFragmentManager.beginTransaction().apply {
			replace(R.id.frameLayout, puzzleFragment)
			commit()
		}
	}
}