package net.minecraft.server.network.packet;

import net.minecraft.network.listener.PacketListener;
import java.io.IOException;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.Packet;

public class VehicleMoveC2SPacket implements Packet<ServerPlayPacketListener>
{
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    
    public VehicleMoveC2SPacket() {
    }
    
    public VehicleMoveC2SPacket(final Entity entity) {
        this.x = entity.x;
        this.y = entity.y;
        this.z = entity.z;
        this.yaw = entity.yaw;
        this.pitch = entity.pitch;
    }
    
    @Override
    public void read(final PacketByteBuf buf) throws IOException {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.yaw = buf.readFloat();
        this.pitch = buf.readFloat();
    }
    
    @Override
    public void write(final PacketByteBuf buf) throws IOException {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeFloat(this.yaw);
        buf.writeFloat(this.pitch);
    }
    
    @Override
    public void apply(final ServerPlayPacketListener listener) {
        listener.onVehicleMove(this);
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
}
