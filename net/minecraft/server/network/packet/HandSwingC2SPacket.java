package net.minecraft.server.network.packet;

import net.minecraft.network.listener.PacketListener;
import java.io.IOException;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.Hand;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.Packet;

public class HandSwingC2SPacket implements Packet<ServerPlayPacketListener>
{
    private Hand hand;
    
    public HandSwingC2SPacket() {
    }
    
    public HandSwingC2SPacket(final Hand hand) {
        this.hand = hand;
    }
    
    @Override
    public void read(final PacketByteBuf buf) throws IOException {
        this.hand = buf.<Hand>readEnumConstant(Hand.class);
    }
    
    @Override
    public void write(final PacketByteBuf buf) throws IOException {
        buf.writeEnumConstant(this.hand);
    }
    
    @Override
    public void apply(final ServerPlayPacketListener listener) {
        listener.onHandSwing(this);
    }
    
    public Hand getHand() {
        return this.hand;
    }
}
