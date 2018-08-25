@file:koma.internal.JvmName("NDArray")
@file:koma.internal.JvmMultifileClass

/**
 * THIS FILE IS AUTOGENERATED, DO NOT MODIFY. EDIT THE FILES IN templates/
 * AND RUN ./gradlew :codegen INSTEAD!
 */

package koma.extensions

import koma.internal.default.generated.ndarray.DefaultGenericNDArray
import koma.internal.default.utils.checkIndices
import koma.internal.default.utils.linearToNIdx
import koma.matrix.doubleFactory
import koma.ndarray.NDArray
import koma.ndarray.NumericalNDArrayFactory
import koma.internal.default.utils.nIdxToLinear
import koma.pow
import koma.matrix.Matrix



@koma.internal.JvmName("fillByte")
inline fun  NDArray<Byte>.fill(f: (idx: IntArray) -> Byte) = apply {
    for ((nd, linear) in this.iterateIndices())
        this.setByte(linear, f(nd))
}

@koma.internal.JvmName("fillByteBoth")
inline fun  NDArray<Byte>.fillBoth(f: (nd: IntArray, linear: Int) -> Byte) = apply {
    for ((nd, linear) in this.iterateIndices())
        this.setByte(linear, f(nd, linear))
}

@koma.internal.JvmName("fillByteLinear")
inline fun  NDArray<Byte>.fillLinear(f: (idx: Int) -> Byte) = apply {
    for (idx in 0 until size)
        this.setByte(idx, f(idx))
}

@koma.internal.JvmName("createByte")
inline fun  NumericalNDArrayFactory<Byte>.create(vararg lengths: Int, filler: (idx: IntArray) -> Byte)
    = NDArray.byteFactory.zeros(*lengths).fill(filler)


/**
 * Returns a new NDArray with the given shape, populated with the data in this array.
 *
 * @param dims Desired dimensions of the output array.
 *
 * @returns A copy of the elements in this array, shaped to the given number of rows and columns,
 *          such that `this.toList() == this.reshape(*dims).toList()`
 *
 * @throws IllegalArgumentException when the product of all of the given `dims` does not equal [size]
 */
@koma.internal.JvmName("reshapeByte")
fun  NDArray<Byte>.reshape(vararg dims: Int): NDArray<Byte> {
    if (dims.reduce { a, b -> a * b } != size)
        throw IllegalArgumentException("$size items cannot be reshaped to ${dims.toList()}")
    var idx = 0
    return NDArray(*dims) { _ -> getByte(idx++) }
}


/**
 * Takes each element in a NDArray, passes them through f, and puts the output of f into an
 * output NDArray.
 *
 * @param f A function that takes in an element and returns an element
 *
 * @return the new NDArray after each element is mapped through f
 */
@koma.internal.JvmName("mapByte")
inline fun  NDArray<Byte>.map(f: (Byte) -> Byte)
    = NDArray.byteFactory.zeros(*shape().toIntArray()).fillLinear { f(this.getByte(it)) }


/**
 * Takes each element in a NDArray, passes them through f, and puts the output of f into an
 * output NDArray. Index given to f is a linear index, depending on the underlying storage
 * major dimension.
 *
 * @param f A function that takes in an element and returns an element. Function also takes
 *      in the linear index of the element's location.
 *
 * @return the new NDArray after each element is mapped through f
 */
@koma.internal.JvmName("mapIndexedByte")
inline fun  NDArray<Byte>.mapIndexed(f: (idx: Int, ele: Byte) -> Byte)
    = NDArray.byteFactory.zeros(*shape().toIntArray()).fillLinear { f(it, this.getByte(it)) }


/**
 * Takes each element in a NDArray and passes them through f.
 *
 * @param f A function that takes in an element
 *
 */
@koma.internal.JvmName("forEachByte")
inline fun  NDArray<Byte>.forEach(f: (ele: Byte) -> Unit) {
    // TODO: Change this back to iteration once there are non-boxing iterators
    for (idx in 0 until size)
        f(getByte(idx))
}
/**
 * Takes each element in a NDArray and passes them through f. Index given to f is a linear
 * index, depending on the underlying storage major dimension.
 *
 * @param f A function that takes in an element. Function also takes
 *      in the linear index of the element's location.
 *
 */
@koma.internal.JvmName("forEachIndexedByte")
inline fun  NDArray<Byte>.forEachIndexed(f: (idx: Int, ele: Byte) -> Unit) {
    // TODO: Change this back to iteration once there are non-boxing iterators
    for (idx in 0 until size)
        f(idx, getByte(idx))
}

/**
 * Takes each element in a NDArray, passes them through f, and puts the output of f into an
 * output NDArray. Index given to f is the full ND index of the element.
 *
 * @param f A function that takes in an element and returns an element. Function also takes
 *      in the ND index of the element's location.
 *
 * @return the new NDArray after each element is mapped through f
 */
@koma.internal.JvmName("mapIndexedNByte")
inline fun  NDArray<Byte>.mapIndexedN(f: (idx: IntArray, ele: Byte) -> Byte): NDArray<Byte>
    = NDArray.byteFactory.zeros(*shape().toIntArray()).fillBoth { nd, linear -> f(nd, getByte(linear)) }


/**
 * Takes each element in a NDArray and passes them through f. Index given to f is the full
 * ND index of the element.
 *
 * @param f A function that takes in an element. Function also takes
 *      in the ND index of the element's location.
 *
 */
@koma.internal.JvmName("forEachIndexedNByte")
inline fun  NDArray<Byte>.forEachIndexedN(f: (idx: IntArray, ele: Byte) -> Unit) {
    for ((nd, linear) in iterateIndices())
        f(nd, getByte(linear))
}

/**
 * Converts this NDArray into a one-dimensional ByteArray in row-major order.
 */
fun  NDArray<Byte>.toByteArray() = ByteArray(size) { getByte(it) }

@koma.internal.JvmName("getRangesByte")
operator fun  NDArray<Byte>.get(vararg indices: IntRange): NDArray<Byte> {
    checkIndices(indices.map { it.last }.toIntArray())
    return DefaultGenericNDArray<Byte>(shape = *indices
            .map { it.last - it.first + 1 }
            .toIntArray()) { newIdxs ->
        val offsets = indices.map { it.first }
        val oldIdxs = newIdxs.zip(offsets).map { it.first + it.second }
        this.getGeneric(*oldIdxs.toIntArray())
    }
}

@koma.internal.JvmName("setByte")
operator fun  NDArray<Byte>.set(vararg indices: Int, value: NDArray<Byte>) {
    val shape = shape()
    val lastIndex = indices.mapIndexed { i, range -> range + value.shape()[i] }
    val outOfBounds = lastIndex.withIndex().any { it.value > shape()[it.index] }
    if (outOfBounds)
        throw IllegalArgumentException("NDArray with shape ${shape()} cannot be " +
                "set at ${indices.toList()} by a ${value.shape()} array " +
                "(out of bounds)")

    val offset = indices.map { it }.toIntArray()
    value.forEachIndexedN { idx, ele ->
        val newIdx = offset.zip(idx).map { it.first + it.second }.toIntArray()
        this.setGeneric(indices=*newIdx, v=ele)
    }
}


operator fun  NDArray<Byte>.get(vararg indices: Int) = getByte(*indices)
operator fun  NDArray<Byte>.set(vararg indices: Int, value: Byte) = setByte(indices=*indices, v=value)


@koma.internal.JvmName("divByte")
operator fun NDArray<Byte>.div(other: Byte) = map { (it/other).toByte() }
@koma.internal.JvmName("timesArrByte")
operator fun NDArray<Byte>.times(other: NDArray<Byte>) = mapIndexedN { idx, ele -> (ele*other.get(*idx)).toByte() }
@koma.internal.JvmName("timesByte")
operator fun NDArray<Byte>.times(other: Byte) = map { (it * other).toByte() }
@koma.internal.JvmName("unaryMinusByte")
operator fun NDArray<Byte>.unaryMinus() = map { (-it).toByte() }
@koma.internal.JvmName("minusByte")
operator fun NDArray<Byte>.minus(other: Byte) = map { (it - other).toByte() }
@koma.internal.JvmName("minusArrByte")
operator fun NDArray<Byte>.minus(other: NDArray<Byte>) = mapIndexedN { idx, ele -> (ele - other.get(*idx)).toByte() }
@koma.internal.JvmName("plusByte")
operator fun NDArray<Byte>.plus(other: Byte) = map { (it + other).toByte() }
@koma.internal.JvmName("plusArrByte")
operator fun NDArray<Byte>.plus(other: NDArray<Byte>) = mapIndexedN { idx, ele -> (ele + other.get(*idx)).toByte() }
@koma.internal.JvmName("powByte")
infix fun NDArray<Byte>.pow(exponent: Int) = map { pow(it.toDouble(), exponent).toByte() }

