package se.dolutions.SmartHome.AlarmToMQTT.Visonic;

public enum EnumSystemStatus {
    ssDisarm(0x00),
    ssExitDelay(0x01),
    ssExitDelay2(0x02),
    ssEntryDelay(0x03),
    ssArmedHome(0x04),
    ssArmedAway(0x05),
    ssUserText(0x06),
    ssDownload(0x07),
    ssProgramming(0x08),
    ssInstaller(0x09),
    ssHomeBypass(0x0A),
    ssAwayBypass(0x0B),
    ssReady(0x0C),
    ssNotReady(0x0D);

    private EnumSystemStatus(int value){
    }
}
