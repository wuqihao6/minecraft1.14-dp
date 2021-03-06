package net.minecraft.client.network.packet;

import net.minecraft.network.listener.PacketListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import java.io.IOException;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.Packet;

public class ExperienceOrbSpawnS2CPacket implements Packet<ClientPlayPacketListener>
{
    private int id;
    private double x;
    private double y;
    private double z;
    private int experience;
    
    public ExperienceOrbSpawnS2CPacket() {
    }
    
    public ExperienceOrbSpawnS2CPacket(final ExperienceOrbEntity experienceOrbEntity) {
        this.id = experienceOrbEntity.getEntityId();
        this.x = experienceOrbEntity.x;
        this.y = experienceOrbEntity.y;
        this.z = experienceOrbEntity.z;
        this.experience = experienceOrbEntity.getExperienceAmount();
    }
    
    @Override
    public void read(final PacketByteBuf buf) throws IOException {
        this.id = buf.readVarInt();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.experience = buf.readShort();
    }
    
    @Override
    public void write(final PacketByteBuf buf) throws IOException {
        buf.writeVarInt(this.id);
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeShort(this.experience);
    }
    
    @Override
    public void apply(final ClientPlayPacketListener listener) {
        listener.onExperienceOrbSpawn(this);
    }
    
    @Environment(EnvType.CLIENT)
    public int getId() {
        return this.id;
    }
    
    @Environment(EnvType.CLIENT)
    public double getX() {
        return this.x;
    }
    
    @Environment(EnvType.CLIENT)
    public double getY() {
        return this.y;
    }
    
    @Environment(EnvType.CLIENT)
    public double getZ() {
        return this.z;
    }
    
    @Environment(EnvType.CLIENT)
    public int getExperience() {
        return this.experience;
    }
}
