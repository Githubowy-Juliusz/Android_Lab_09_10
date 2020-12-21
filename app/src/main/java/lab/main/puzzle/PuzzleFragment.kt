package lab.main.puzzle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import lab.main.R

class PuzzleFragment : Fragment(R.layout.puzzle_fragment) {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val layout = view.findViewById<ConstraintLayout>(R.id.puzzleLayout)
		val restartButton = view.findViewById<Button>(R.id.puzzleRestartButton)
		val movesText = view.findViewById<TextView>(R.id.puzzleMovesText)
		val position1 = view.findViewById<ImageView>(R.id.puzzlePosition1)
		val position2 = view.findViewById<ImageView>(R.id.puzzlePosition2)
		val position3 = view.findViewById<ImageView>(R.id.puzzlePosition3)
		val position4 = view.findViewById<ImageView>(R.id.puzzlePosition4)
		val position5 = view.findViewById<ImageView>(R.id.puzzlePosition5)
		val position6 = view.findViewById<ImageView>(R.id.puzzlePosition6)
		val position7 = view.findViewById<ImageView>(R.id.puzzlePosition7)
		val position8 = view.findViewById<ImageView>(R.id.puzzlePosition8)
		val position9 = view.findViewById<ImageView>(R.id.puzzlePosition9)
	}
}