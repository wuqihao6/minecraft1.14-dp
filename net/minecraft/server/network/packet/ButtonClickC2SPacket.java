package net.minecraft.server.network.packet;

import net.minecraft.network.listener.PacketListener;
import java.io.IOException;
import net.minecraft.util.PacketByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.Packet;

public class ButtonClickC2SPacket implements Packet<ServerPlayPacketListener>
{
    private int syncId;
    private int buttonId;
    
    public ButtonClickC2SPacket() {
    }
    
    @Environment(EnvType.CLIENT)
    public ButtonClickC2SPacket(final int syncId, final int buttonId) {
        this.syncId = syncId;
        this.buttonId = buttonId;
    }
    
    @Override
    public void apply(final ServerPlayPacketListener listener) {
        listener.onButtonClick(this);
    }
    
    @Override
    public void read(final PacketByteBuf buf) throws IOException {
        this.syncId = buf.readByte();
        this.buttonId = buf.readByte();
    }
    
    @Override
    public void write(final PacketByteBuf buf) throws IOException {
        buf.writeByte(this.syncId);
        buf.writeByte(this.buttonId);
    }
    
    public int getSyncId() {
        return this.syncId;
    }
    
    public int getButtonId() {
        return this.buttonId;
    }
}
