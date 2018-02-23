package koma.matrix.default

import koma.*
import koma.extensions.*
import koma.matrix.*

class DefaultDoubleMatrix (val rows: Int, 
                          val cols: Int): Matrix<Double> {
    val storage = DoubleArray(rows*cols)

    
    override fun div(other: Double): Matrix<Double>
            = this.mapIndexed { _, _, ele -> ele/other}

    
    override fun div(other: Int): Matrix<Double>
            = this.mapIndexed { _, _, ele -> ele/other}


    override fun times(other: Matrix<Double>): Matrix<Double> {
        val out = getFactory().zeros(this.numRows(), other.numCols())
        out.forEachIndexed { row, col, _ ->
            for (cursor in 0 until this.numCols())
                out[row,col] += this[row, cursor]*other[cursor, col]
        }
        return out
    }
    
    override fun times(other: Double): Matrix<Double> 
            = this.map { it*other }

    override fun unaryMinus(): Matrix<Double> 
            = this.map { it*-1 }

    override fun minus(other: Double): Matrix<Double>
            = this.map { it - other }
    
    override fun minus(other: Matrix<Double>): Matrix<Double> 
            = this.mapIndexed { row, col, ele -> ele - other[row,col] }

    override fun plus(other: Double): Matrix<Double> 
            = this.map { it + other }

    override fun plus(other: Matrix<Double>): Matrix<Double> 
            = this.mapIndexed { row, col, ele -> ele + other[row,col] }

    override fun transpose(): Matrix<Double> 
            = getFactory()
            .zeros(numCols(),numRows())
            .fill { row, col -> this[col,row] }

    override fun elementTimes(other: Matrix<Double>): Matrix<Double> 
            = this.mapIndexed { row, col, ele -> ele*other[row,col] }

    
    override fun epow(other: Double): Matrix<Double> 
            = this.mapIndexed { _, _, ele -> pow(ele.toDouble(), other.toDouble()).toDouble() }

    
    override fun epow(other: Int): Matrix<Double>
            = this.mapIndexed { _, _, ele -> pow(ele.toDouble(), other.toDouble()).toDouble() }

    override fun numRows(): Int = this.rows
    override fun numCols(): Int = this.cols

    private fun setStorage(i: Int, v: Double) {
        storage[i] = v
    }
    private fun setStorage(i: Int, j: Int, v: Double) {
        checkBounds(i,j)
        storage[this.cols*i+j] = v
    }

    private fun getStorage(i: Int, j: Int): Double {
        checkBounds(i,j)
        return storage[this.cols*i+j]
    }

    private fun getStorage(i: Int): Double 
            = storage[i]
    
    override fun copy(): Matrix<Double> 
            = this.map { it }
    
    
    override fun getInt(i: Int, j: Int): Int = this.getStorage(i,j).toInt()
    override fun getDouble(i: Int, j: Int): Double = this.getStorage(i,j).toDouble()
    override fun getFloat(i: Int, j: Int): Float = this.getStorage(i,j).toFloat()
    override fun getGeneric(i: Int, j: Int): Double = this.getStorage(i,j)
    override fun getInt(i: Int): Int = this.getStorage(i).toInt()
    override fun getDouble(i: Int): Double = this.getStorage(i).toDouble()
    override fun getFloat(i: Int): Float = this.getStorage(i).toFloat()
    override fun getGeneric(i: Int): Double = this.getStorage(i)
    override fun setInt(i: Int, v: Int) { this.setStorage(i, v.toDouble())}
    override fun setDouble(i: Int, v: Double) { this.setStorage(i, v.toDouble())}
    override fun setFloat(i: Int, v: Float) { this.setStorage(i, v.toDouble())}
    override fun setGeneric(i: Int, v: Double) { this.setStorage(i, v)}
    override fun setInt(i: Int, j: Int, v: Int) { this.setStorage(i, j, v.toDouble())}
    override fun setDouble(i: Int, j: Int, v: Double) { this.setStorage(i, j, v.toDouble())}
    override fun setFloat(i: Int, j: Int, v: Float) { this.setStorage(i, j, v.toDouble())}
    override fun setGeneric(i: Int, j: Int, v: Double) { this.setStorage(i, j, v)}
    override fun getDoubleData(): DoubleArray = storage.map { it.toDouble() }.toDoubleArray()
    override fun getRow(row: Int): Matrix<Double> {
        checkBounds(row, 0)
        val out = getFactory().zeros(1,cols)
        for (i in 0 until cols)
            out[i] = this[row, i]
        return out
    }
    override fun getCol(col: Int): Matrix<Double> {
        checkBounds(0,col)
        val out = getFactory().zeros(rows,1)
        for (i in 0 until rows)
            out[i] = this[i, col]
        return out
    }

    override fun setCol(index: Int, col: Matrix<Double>) {
        checkBounds(0,index)
        for (i in 0 until rows)
            this[i, index] = col[i]
    }

    override fun setRow(index: Int, row: Matrix<Double>) {
        checkBounds(index, 0)
        for (i in 0 until cols)
            this[index, i] = row[i]
    }

    override fun chol(): Matrix<Double> {
        TODO("not implemented")
    }

    override fun LU(): Triple<Matrix<Double>, Matrix<Double>, Matrix<Double>> {
        TODO("not implemented")
    }

    override fun QR(): Pair<Matrix<Double>, Matrix<Double>> {
        TODO("not implemented")
    }

    override fun SVD(): Triple<Matrix<Double>, Matrix<Double>, Matrix<Double>> {
        TODO("not implemented")
    }

	override fun expm(): Matrix<Double> {
        TODO("not implemented")
    }

    override fun solve(other: Matrix<Double>): Matrix<Double> {
        TODO("not implemented")
    }

    override fun inv(): Matrix<Double> {
        TODO("not implemented")
    }

    override fun det(): Double {
        TODO("not implemented")
    }

    override fun pinv(): Matrix<Double> {
        TODO("not implemented")
    }

    override fun normF(): Double {
        TODO("not implemented")
    }

    override fun normIndP1(): Double {
        TODO("not implemented")
    }

    override fun elementSum(): Double 
            = this.toIterable().reduce { a, b -> a + b }

    override fun diag(): Matrix<Double> 
            = getFactory()
            .zeros(numRows(),1)
            .fill{ row, _ -> this[row,row] }

    override fun max(): Double = this[argMax()]
    override fun mean(): Double = elementSum()/(numRows()*numCols())
    override fun min(): Double = this[argMin()]

    override fun argMax(): Int {
        var highest= Double.MIN_VALUE
        var highestIdx = -1
        for (i in 0 until numRows()*numCols())
            if(this[i] > highest) {
                highest = this[i]
                highestIdx = i
            }
        return highestIdx
    }

    override fun argMin(): Int {
        var lowest = Double.MAX_VALUE
        var lowestIdx = -1
        for (i in 0 until numRows()*numCols())
            if(this[i] < lowest) {
                lowest = this[i]
                lowestIdx = i
            }
        return lowestIdx
    }

    override fun trace(): Double {
        TODO("not implemented")
    }

    override fun T(): Matrix<Double> = this.transpose()

    override fun getBaseMatrix(): Any 
            = storage
    override fun getFactory(): MatrixFactory<Matrix<Double>> 
            = DefaultDoubleMatrixFactory()
    
    private fun checkBounds(row: Int, col: Int) {
        if (row >= rows || col >= cols)
            throw IllegalArgumentException("row/col index out of bounds")
    }
}
