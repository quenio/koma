/**
 * THIS FILE IS AUTOGENERATED, DO NOT MODIFY. EDIT THE FILES IN templates/
 * AND RUN ./gradlew :codegen INSTEAD!
 */

package koma.internal.default.generated.ndarray

import koma.extensions.fill
import koma.ndarray.*

class DefaultDoubleNDArrayFactory: NumericalNDArrayFactory<Double> {
    override fun createGeneric(lengths: IntArray, filler: (IntArray)->Double) = DefaultDoubleNDArray(*lengths, init=filler)

    override fun zeros(vararg lengths: Int) = DefaultDoubleNDArray(*lengths) { 0.0 }

    override fun ones(vararg lengths: Int) = DefaultDoubleNDArray(*lengths) { 1.0 }

    override fun rand(vararg lengths: Int) = DefaultDoubleNDArray(*lengths) {
        koma.internal.getRng().nextDouble().toDouble()
    }

    override fun randn(vararg lengths: Int) = DefaultDoubleNDArray(*lengths) {
        koma.internal.getRng().nextGaussian().toDouble()
    }
}
