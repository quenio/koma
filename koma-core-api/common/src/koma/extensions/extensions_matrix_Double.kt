@file:koma.internal.JvmName("MatrixExtensions")
@file:koma.internal.JvmMultifileClass

/**
 * THIS FILE IS AUTOGENERATED, DO NOT MODIFY. EDIT THE FILES IN templates/
 * AND RUN ./gradlew :codegen INSTEAD!
 */

package koma.extensions

import koma.matrix.Matrix
import koma.internal.KomaJsName
import koma.internal.KomaJvmName

/**
 * Checks to see if any element in the matrix causes f to return true.
 *
 * @param f A function which takes in an element from the matrix and returns a Boolean.
 *
 * @return Whether or not any element, when passed into f, causes f to return true.
 */
@KomaJsName("anyDouble")
@KomaJvmName("anyDouble")
inline fun  Matrix<Double>.any(f: (Double) -> Boolean): Boolean {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            if (f(this[row, col]))
                return true
    return false
}

/**
 * Checks to see if all elements cause f to return true.
 *
 * @param f A function which takes in an element from the matrix and returns a Boolean.
 *
 * @return Returns true only if f is true for all elements of the input matrix
 */
@KomaJsName("allDouble")
@KomaJvmName("allDouble")
inline fun  Matrix<Double>.all(f: (Double) -> Boolean): Boolean {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            if (!f(this[row, col]))
                return false
    return true
}

/**
 * Fills the matrix with the values returned by the input function.
 *
 * @param f A function which takes row,col and returns the value to fill. Note that
 * the return type must be the matrix primitive type (e.g. Double).
 */
@KomaJsName("fillDouble")
@KomaJvmName("fillDouble")
inline fun  Matrix<Double>.fill(f: (row: Int, col: Int) -> Double): Matrix<Double> {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            this[row, col] = f(row, col)
    return this
}

/**
 * Passes each element in row major order into a function.
 *
 * @param f A function that takes in an element
 *
 */
@KomaJsName("forEachDouble")
@KomaJvmName("forEachDouble")
inline fun  Matrix<Double>.forEach(f: (Double) -> Unit) {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            f(this[row, col])
}

/**
 * Passes each element in row major order into a function along with its index location.
 *
 * @param f A function that takes in a row,col position and an element value
 */
@KomaJsName("forEachIndexedDouble")
@KomaJvmName("forEachIndexedDouble")
inline fun  Matrix<Double>.forEachIndexed(f: (row: Int, col: Int, ele: Double) -> Unit) {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            f(row, col, this[row, col])
}


/**
 * Takes each element in a matrix, passes them through f, and puts the output of f into an
 * output matrix. This process is done in row-major order.
 *
 * @param f A function that takes in an element and returns an element
 *
 * @return the new matrix after each element is mapped through f
 */
@KomaJsName("mapDouble")
@KomaJvmName("mapDouble")
inline fun  Matrix<Double>.map(f: (Double) -> Double): Matrix<Double> {
    val out = this.getFactory().zeros(this.numRows(), this.numCols())
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            out[row, col] = f(this[row, col])
    return out
}

/**
 * Takes each element in a matrix, passes them through f, and puts the output of f into an
 * output matrix. This process is done in row-major order.
 *
 * @param f A function that takes in an element and returns an element. Function also takes
 *      in the row, col index of the element's location.
 *
 * @return the new matrix after each element is mapped through f
 */
@KomaJsName("mapIndexedDouble")
@KomaJvmName("mapIndexedDouble")
inline fun  Matrix<Double>.mapIndexed(f: (row: Int, col: Int, ele: Double) -> Double): Matrix<Double> {
    val out = this.getFactory().zeros(this.numRows(), this.numCols())
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            out[row, col] = f(row, col, this[row, col])
    return out
}


@KomaJsName("getDouble")
operator fun  Matrix<Double>.get(i: Int, j: Int): Double = getDouble(i, j)
/**
 * Gets the ith element in the matrix. If 2D, selects elements in row-major order.
 */
@KomaJsName("get1DDouble")
operator fun  Matrix<Double>.get(i: Int): Double = getDouble(i)

/**
 * Allow slicing, e.g. ```matrix[1..2, 3..4]```. Note that the range 1..2 is inclusive, so
 * it will retrieve row 1 and 2. Use 1.until(2) for a non-inclusive range.
 *
 * @param rows the set of rows to select
 * @param cols the set of columns to select
 *
 * @return a new matrix containing the submatrix.
 */
@KomaJvmName("getRangesDouble")
@KomaJsName("getRangesDouble")
operator fun  Matrix<Double>.get(rows: IntRange, cols: IntRange): Matrix<Double>
{
    val wrows = wrapRange(rows, numRows())
    val wcols = wrapRange(cols, numCols())

    val out = this.getFactory().zeros(wrows.endInclusive - wrows.start + 1,
            wcols.endInclusive - wcols.start + 1)
    for (row in wrows)
        for (col in wcols)
            out[row - wrows.start, col - wcols.start] = this[row, col]
    return out
}
/**
 * Allows for slicing of the rows and selection of a single column
 */
@KomaJvmName("setRowRangeDouble")
@KomaJsName("getRowRangeDouble")
operator fun  Matrix<Double>.get(rows: IntRange, cols: Int) = this[rows, cols..cols]

/**
 * Allows for slicing of the cols and selection of a single row
 */
@KomaJvmName("getColRangeDouble")
@KomaJsName("getColRangeDouble")
operator fun  Matrix<Double>.get(rows: Int, cols: IntRange) = this[rows..rows, cols]


/**
 * Set the ith element in the matrix. If 2D, selects elements in row-major order.
 */
@KomaJvmName("set1DDouble")
@KomaJsName("set1DDouble")
operator fun  Matrix<Double>.set(i: Int, v: Double) = setDouble(i, v)
@KomaJvmName("set2DDouble")
@KomaJsName("set2DDouble")
operator fun  Matrix<Double>.set(i: Int, j: Int, v: Double) = setDouble(i, j, v)
/**
 * Allow assignment to a slice, e.g. ```matrix[1..2, 3..4]```=something. Note that the range 1..2 is inclusive, so
 * it will retrieve row 1 and 2. Use 1.until(2) for a non-inclusive range.
 *
 * @param rows the set of rows to select
 * @param cols the set of columns to select
 * @param value the matrix to set the subslice to
 *
 */
@KomaJvmName("setRangesDouble")
@KomaJsName("setRangesDouble")
operator fun  Matrix<Double>.set(rows: IntRange, cols: IntRange, value: Matrix<Double>)
{
    val wrows = wrapRange(rows, numRows())
    val wcols = wrapRange(cols, numCols())

    for (i in wrows)
        for (j in wcols)
            this[i, j] = value[i - wrows.start, j - wcols.start]
}
@KomaJsName("setRangesScalarDouble")
operator fun  Matrix<Double>.set(rows: IntRange, cols: IntRange, value: Double)
{
    val wrows = wrapRange(rows, numRows())
    val wcols = wrapRange(cols, numCols())

    for (i in wrows)
        for (j in wcols)
            this[i, j] = value
}
/**
 * Allow assignment to a slice, e.g. ```matrix[2, 3..4]```=something. Note that the range 3..4 is inclusive, so
 * it will retrieve col 3 and 4. Use 1.until(2) for a non-inclusive range.
 *
 * @param rows the row to select
 * @param cols the set of columns to select
 * @param value the matrix to set the subslice to
 *
 */
@KomaJvmName("setColRangeDouble")
@KomaJsName("setColRangeDouble")
operator fun  Matrix<Double>.set(rows: Int, cols: IntRange, value: Matrix<Double>)
{
    this[rows..rows, cols] = value
}
@KomaJvmName("setColRangeScalarDouble")
@KomaJsName("setColRangeScalarDouble")
operator fun  Matrix<Double>.set(rows: Int, cols: IntRange, value: Double)
{
    this[rows..rows, cols] = value
}
/**
 * Allow assignment to a slice, e.g. ```matrix[1..2, 3]```=something. Note that the range 1..2 is inclusive, so
 * it will retrieve row 1 and 2. Use 1.until(2) for a non-inclusive range.
 *
 * @param rows the set of rows to select
 * @param cols the column to select
 * @param value the matrix to set the subslice to
 *
 */
@KomaJvmName("setRowRangeDouble")
@KomaJsName("setRowRangeDouble")
operator fun  Matrix<Double>.set(rows: IntRange, cols: Int, value: Matrix<Double>) {
    this[rows, cols..cols] = value
}
@KomaJsName("setRowRangeScalarDouble")
operator fun  Matrix<Double>.set(rows: IntRange, cols: Int, value: Double) {
    this[rows, cols..cols] = value
}


@KomaJvmName("set1DDoubleFromInt")
@KomaJsName("set1DDoubleFromInt")
operator fun Matrix<Double>.set(i: Int, v: Int) = this.setDouble(i, v.toDouble())
@KomaJvmName("set2DDoubleFromInt")
@KomaJsName("set2DDoubleFromInt")
operator fun Matrix<Double>.set(i: Int, j: Int, v: Int) = this.setDouble(i, j, v.toDouble())

@KomaJvmName("allCloseDouble")
fun Matrix<Double>.allClose(other: Matrix<Double>, rtol:Double=1e-05, atol:Double=1e-08): Boolean {
    if(other.numRows() != numRows() || other.numCols() != numCols())
        return false
    for(row in 0 until this.numRows()) {
        for (col in 0 until this.numCols()) {
            val err = kotlin.math.abs(this[row, col] - other[row, col])
            if (err > atol + rtol * kotlin.math.abs(this[row, col]))
                return false
        }
    }
    return true
}