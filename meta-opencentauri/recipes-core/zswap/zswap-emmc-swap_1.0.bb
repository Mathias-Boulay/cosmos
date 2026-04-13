SUMMARY = "zswap configuration with tiny eMMC backing swap"
DESCRIPTION = "Configures zswap (lz4) with a bounded compressed pool and a tiny backing swapfile."
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"

SRC_URI = "file://zswap-emmc-swap"

inherit update-rc.d

INITSCRIPT_NAME = "zswap-emmc-swap"
INITSCRIPT_PARAMS = "defaults 21 79"

RDEPENDS:${PN} = "util-linux-swaponoff util-linux-mkswap"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/zswap-emmc-swap ${D}${sysconfdir}/init.d/
}

FILES:${PN} = "${sysconfdir}/init.d/zswap-emmc-swap"
