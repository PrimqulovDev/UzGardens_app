package app.test.uzGardens.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load

fun AppCompatImageView.loadAny(uri: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadAny.context)) }
        .build()
    load(uri, imageLoader = imageLoader)
}