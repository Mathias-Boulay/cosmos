SUMMARY = "Chocolate Doom - historically accurate Doom source port"
DESCRIPTION = "Chocolate Doom aims to accurately reproduce the original DOS \
version of Doom and other games based on the Doom engine."
HOMEPAGE = "https://www.chocolate-doom.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.md;md5=60d644347832d2dd9534761f6919e2a6"

SRC_URI = "git://github.com/chocolate-doom/chocolate-doom.git;protocol=https;branch=master"

SRCREV = "410d96855b5df5410ff591a90efeafa889119224"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

# ---------------------------------------------------------------------------
# Dependencies
# ---------------------------------------------------------------------------
# SDL2 on framebuffer -- make sure your SDL2 is built with fbdev/kmsdrm
# support. You may need in your local.conf or distro config:
#   PACKAGECONFIG:pn-libsdl2 = "kmsdrm alsa"
# (drop x11/wayland if you don't want them)
# ---------------------------------------------------------------------------

DEPENDS = " \
    libsdl2 \
    libpng \
    libsamplerate0 \
"

# Optional: add these back when you have audio/networking
# DEPENDS += "libsdl2-mixer libsdl2-net fluidsynth"

# ---------------------------------------------------------------------------
# CMake configuration
# ---------------------------------------------------------------------------
EXTRA_OECMAKE = " \
    -DENABLE_SDL2_MIXER=OFF \
    -DENABLE_SDL2_NET=OFF \
"

do_install() {
    install -d ${D}${bindir}
    for bin in chocolate-doom chocolate-heretic chocolate-hexen chocolate-strife chocolate-server chocolate-setup; do
        install -m 0755 ${B}/src/${bin} ${D}${bindir}/
    done
}

FILES:${PN} = "${bindir}/*"

# ---------------------------------------------------------------------------
# Framebuffer / runtime hints (Allwinner R528)
# ---------------------------------------------------------------------------
# The R528 has a DRM/KMS display engine driver (sun8i-mixer + sun4i-drm).
# Use KMS/DRM for SDL2:
#   SDL_VIDEODRIVER=kmsdrm
#
# If you're using legacy fbdev instead:
#   SDL_VIDEODRIVER=fbcon
#
# For your 480x272 display:
#   chocolate-doom -width 480 -height 272 -fullscreen
#
# Or set in ~/.local/share/chocolate-doom/default.cfg:
#   fullscreen 1
#   screen_width 480
#   screen_height 272
#
# WAD files:
#   Place doom1.wad (shareware) or doom.wad in DOOMWADDIR
#   export DOOMWADDIR=/usr/share/games/doom
# ---------------------------------------------------------------------------
