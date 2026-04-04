SUMMARY = "Lightweight and fast MJPEG-HTTP streamer"
DESCRIPTION = "µStreamer is a lightweight and very quick server to stream MJPEG video from any V4L2 device to the net."
HOMEPAGE = "https://github.com/pikvm/ustreamer"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "88460b72e191035d04355e25106af817cbfe069e"
SRC_URI = "git://github.com/pikvm/ustreamer.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

DEPENDS = "libevent libjpeg-turbo libbsd pkgconfig-native"

EXTRA_OEMAKE = " \
    PREFIX=${prefix} \
    DESTDIR=${D} \
    CC='${CC}' \
    PKG_CONFIG='${PKG_CONFIG}' \
    CFLAGS='${CFLAGS}' \
    LDFLAGS='${LDFLAGS}' \
    WITH_PYTHON=0 \
    WITH_JANUS=0 \
    WITH_GPIO=0 \
    WITH_SYSTEMD=0 \
    WITH_V4P=1 \
"

do_install() {
    oe_runmake install
}
