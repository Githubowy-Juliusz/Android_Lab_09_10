package lab.main.puzzle

class PuzzleGame {
	//solved state
	//1, 2, 3
	//4, 5, 6
	//7, 8, 0

	//blocks to positions
	private val _blocks = hashMapOf(
		1 to 1, 2 to 2, 3 to 3,
		4 to 4, 5 to 5, 6 to 6,
		7 to 7, 8 to 8, 0 to 9
	)
	private val solved = _blocks.toMap()
	val isSolved: Boolean
		get() {
			return _blocks == solved
		}
	val blocks: Map<Int, Int>
		get() {
			return _blocks.toMap()
		}

	//Inverse Map
	val positions: Map<Int, Int>
		get() {
			return _blocks.entries.associateBy({ it.value }) { it.key }
		}
	var moves = 0
		private set
	val availableMoves: List<Direction>
		get() {
			val directions = mutableListOf<Direction>()
			val zero = _blocks[0]
			if(zero in (1..6))
				directions.add(Direction.DOWN)
			if(zero in (4..9))
				directions.add(Direction.UP)
			if(zero in listOf(1, 2, 4, 5, 7, 8))
				directions.add(Direction.RIGHT)
			if(zero in listOf(2, 3, 5, 6, 8, 9))
				directions.add(Direction.LEFT)
			return directions.toList()
		}

	init {
		while(isSolved)
			for(i in (1..300))
				unconditionalMove(availableMoves.random())
	}

	fun move(direction: Direction): Boolean {
		if(direction !in availableMoves)
			return false
		if(isSolved)
			return false
		unconditionalMove(direction)
		moves++
		return true
	}

	private fun unconditionalMove(direction: Direction) {
		val zeroPosition = _blocks[0] ?: error("Didn't find position for block 0")
		val movedKey =
			_blocks.filterValues { zeroPosition + direction.value == it }.keys.first()
		_blocks[0] = zeroPosition + direction.value
		_blocks[movedKey] = zeroPosition
	}
}