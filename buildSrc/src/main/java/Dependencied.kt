/**
 * des
 *
 * @author hellobrothers
 * @data 2020/12/18
 */

object Version{
    const val minSdk = 19
}

object Deps{
    const val appcompat = "androidx.appcompat:appcompat:1.1.0"
    const val core_ktx = "androidx.core:core-ktx:1.3.0"
    const val constraintlayout  = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val preference = "androidx.preference:preference:1.1.1"

    val androidxLibs = arrayListOf<String>(appcompat, core_ktx, constraintlayout, preference)
}

