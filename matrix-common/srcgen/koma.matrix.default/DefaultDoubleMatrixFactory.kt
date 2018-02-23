package koma.matrix.default

import koma.*
import koma.matrix.*
import koma.extensions.*

class DefaultDoubleMatrixFactory: MatrixFactory<Matrix<Double>> {
    override fun zeros(rows: Int, cols: Int) 
            = DefaultDoubleMatrix(rows, cols)

    override fun create(data: IntRange): Matrix<Double> {
        val input = data.map { it.toDouble() }
        val out = DefaultDoubleMatrix(1, input.size)
        input.forEachIndexed { idx, ele -> out[idx] = ele }
        return out
    }    
    override fun create(data: DoubleArray): Matrix<Double> {
        val out = DefaultDoubleMatrix(1, data.size)
        data.forEachIndexed { idx, ele -> out[idx] = ele.toDouble() }
        return out
    }

    override fun create(data: Array<DoubleArray>): Matrix<Double> {
        val out = DefaultDoubleMatrix(data.size, data[0].size)
        data.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, ele -> 
                out[rowIdx, colIdx] = ele.toDouble()
            }
        }
        return out
    }

    override fun ones(rows: Int, cols: Int): Matrix<Double>
            = zeros(rows, cols).fill {_,_-> 1.toDouble()}

    override fun eye(size: Int): Matrix<Double> 
            = eye(size, size)
    override fun eye(rows: Int, cols: Int): Matrix<Double>
            = zeros(rows, cols)
            .fill {row,col->if (row==col) 1.toDouble() else 0.toDouble() }


    override fun rand(rows: Int, cols: Int): Matrix<Double>
            = zeros(rows, cols)
            .fill { _, _ -> koma.internal.rng.nextDouble().toDouble()}
    

    override fun randn(rows: Int, cols: Int): Matrix<Double>
            = zeros(rows, cols)
            .fill { _, _ -> koma.internal.rng.nextGaussian().toDouble()}
    

    override fun arange(start: Double, stop: Double, increment: Double): Matrix<Double> {
        TODO("not implemented") 
    }

    override fun arange(start: Double, stop: Double): Matrix<Double> {
        TODO("not implemented") 
    }

    override fun arange(start: Int, stop: Int, increment: Int): Matrix<Double> {
        TODO("not implemented") 
    }

    override fun arange(start: Int, stop: Int): Matrix<Double> {
        TODO("not implemented") 
    }
}
