package com.api.azure.vm;

import com.microsoft.azure.management.compute.*;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;

public class VMController {
    public static void main(String[] args) {
        VirtualMachine newLinuxVm = azure.virtualMachines().define(linuxVmName)
                .withRegion(Region.CHINA_EAST)
                .withNewResourceGroup("ANALYTICS-RG-CN-PROD")
                .withNewPrimaryNetwork("40.73.103.81")
                .withPrimaryPrivateIpAddressDynamic()
                .withoutPrimaryPublicIpAddress()
                .withPopularLinuxImage(KnownLinuxVirtualMachineImage.UBUNTU_SERVER_16_04_LTS)
                .withRootUsername("sondy")
                .withSshKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCgoLapbV4uu8j2smYBuuwtBtl332t634XdiRyBHZ/82Xk3R/TisGcD73taxmAoX6qOjROrsnJ+Av5tx5UUz0xyKBCskPJa8Hc92J4LLCMhc5PQMyPuUkPXlgGf2IAFTJMryRJmowjYcGW9OLms5afuDwhAqwKLcafD/vxUImgCJDM3PbpmlNuyjaoQX3tWPNJyvIYaKdP+2yLQpMWMcTGiOx+n9drz/WgoHMLJuCsTy2P3w3TQv4XCoKX/Y1c8iSsaOEXkDKxAZNXAadawB05vbXFPAhiNPhw7fuNCZK3ykqX2ofodYaDneYRvinJBHsfhzPlsbT7NVvAv3F04s26D sondywoo@sondyPCHome.local")
                .withSize(VirtualMachineSizeTypes.STANDARD_D3_V2)
                .create();
    }
}
