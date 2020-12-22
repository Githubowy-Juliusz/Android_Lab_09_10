package lab.main

import lab.main.puzzle.PuzzleGame
import org.junit.Assert.assertEquals
import org.junit.Test

class Tests {
	@Test
	fun test1() {
		val game = PuzzleGame()
		println(game.availableMoves)
		println(game.blocks)
		println(game.moves)
		println(game.positions)
		println(game.isSolved)
		assertEquals("", "")
	}
}