package net.minecraft.network;

import java.util.Iterator;
import net.minecraft.server.network.packet.LoginQueryResponseC2SPacket;
import net.minecraft.server.network.packet.LoginKeyC2SPacket;
import net.minecraft.server.network.packet.LoginHelloC2SPacket;
import net.minecraft.client.network.packet.LoginQueryRequestS2CPacket;
import net.minecraft.client.network.packet.LoginCompressionS2CPacket;
import net.minecraft.client.network.packet.LoginSuccessS2CPacket;
import net.minecraft.client.network.packet.LoginHelloS2CPacket;
import net.minecraft.client.network.packet.LoginDisconnectS2CPacket;
import net.minecraft.client.network.packet.QueryPongS2CPacket;
import net.minecraft.server.network.packet.QueryPingC2SPacket;
import net.minecraft.client.network.packet.QueryResponseS2CPacket;
import net.minecraft.server.network.packet.QueryRequestC2SPacket;
import net.minecraft.server.network.packet.PlayerInteractItemC2SPacket;
import net.minecraft.server.network.packet.PlayerInteractBlockC2SPacket;
import net.minecraft.server.network.packet.SpectatorTeleportC2SPacket;
import net.minecraft.server.network.packet.HandSwingC2SPacket;
import net.minecraft.server.network.packet.UpdateSignC2SPacket;
import net.minecraft.server.network.packet.UpdateStructureBlockC2SPacket;
import net.minecraft.server.network.packet.UpdateJigsawC2SPacket;
import net.minecraft.server.network.packet.CreativeInventoryActionC2SPacket;
import net.minecraft.server.network.packet.UpdateCommandBlockMinecartC2SPacket;
import net.minecraft.server.network.packet.UpdateCommandBlockC2SPacket;
import net.minecraft.server.network.packet.UpdateSelectedSlotC2SPacket;
import net.minecraft.server.network.packet.UpdateBeaconC2SPacket;
import net.minecraft.server.network.packet.SelectVillagerTradeC2SPacket;
import net.minecraft.server.network.packet.AdvancementTabC2SPacket;
import net.minecraft.server.network.packet.ResourcePackStatusC2SPacket;
import net.minecraft.server.network.packet.RenameItemC2SPacket;
import net.minecraft.server.network.packet.RecipeBookDataC2SPacket;
import net.minecraft.server.network.packet.PlayerInputC2SPacket;
import net.minecraft.server.network.packet.ClientCommandC2SPacket;
import net.minecraft.server.network.packet.PlayerActionC2SPacket;
import net.minecraft.server.network.packet.UpdatePlayerAbilitiesC2SPacket;
import net.minecraft.server.network.packet.CraftRequestC2SPacket;
import net.minecraft.server.network.packet.PickFromInventoryC2SPacket;
import net.minecraft.server.network.packet.BoatPaddleStateC2SPacket;
import net.minecraft.server.network.packet.VehicleMoveC2SPacket;
import net.minecraft.server.network.packet.PlayerMoveC2SPacket;
import net.minecraft.server.network.packet.UpdateDifficultyLockC2SPacket;
import net.minecraft.server.network.packet.KeepAliveC2SPacket;
import net.minecraft.server.network.packet.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.packet.QueryEntityNbtC2SPacket;
import net.minecraft.server.network.packet.BookUpdateC2SPacket;
import net.minecraft.server.network.packet.CustomPayloadC2SPacket;
import net.minecraft.server.network.packet.GuiCloseC2SPacket;
import net.minecraft.server.network.packet.ClickWindowC2SPacket;
import net.minecraft.server.network.packet.ButtonClickC2SPacket;
import net.minecraft.server.network.packet.GuiActionConfirmC2SPacket;
import net.minecraft.server.network.packet.RequestCommandCompletionsC2SPacket;
import net.minecraft.server.network.packet.ClientSettingsC2SPacket;
import net.minecraft.server.network.packet.ClientStatusC2SPacket;
import net.minecraft.server.network.packet.ChatMessageC2SPacket;
import net.minecraft.server.network.packet.UpdateDifficultyC2SPacket;
import net.minecraft.server.network.packet.QueryBlockNbtC2SPacket;
import net.minecraft.server.network.packet.TeleportConfirmC2SPacket;
import net.minecraft.client.network.packet.SynchronizeTagsS2CPacket;
import net.minecraft.client.network.packet.SynchronizeRecipesS2CPacket;
import net.minecraft.client.network.packet.EntityPotionEffectS2CPacket;
import net.minecraft.client.network.packet.EntityAttributesS2CPacket;
import net.minecraft.client.network.packet.AdvancementUpdateS2CPacket;
import net.minecraft.client.network.packet.EntityPositionS2CPacket;
import net.minecraft.client.network.packet.ItemPickupAnimationS2CPacket;
import net.minecraft.client.network.packet.TagQueryResponseS2CPacket;
import net.minecraft.client.network.packet.PlayerListHeaderS2CPacket;
import net.minecraft.client.network.packet.StopSoundS2CPacket;
import net.minecraft.client.network.packet.PlaySoundS2CPacket;
import net.minecraft.client.network.packet.PlaySoundFromEntityS2CPacket;
import net.minecraft.client.network.packet.TitleS2CPacket;
import net.minecraft.client.network.packet.WorldTimeUpdateS2CPacket;
import net.minecraft.client.network.packet.PlayerSpawnPositionS2CPacket;
import net.minecraft.client.network.packet.ScoreboardPlayerUpdateS2CPacket;
import net.minecraft.client.network.packet.TeamS2CPacket;
import net.minecraft.client.network.packet.EntityPassengersSetS2CPacket;
import net.minecraft.client.network.packet.ScoreboardObjectiveUpdateS2CPacket;
import net.minecraft.client.network.packet.HealthUpdateS2CPacket;
import net.minecraft.client.network.packet.ExperienceBarUpdateS2CPacket;
import net.minecraft.client.network.packet.EntityEquipmentUpdateS2CPacket;
import net.minecraft.client.network.packet.EntityVelocityUpdateS2CPacket;
import net.minecraft.client.network.packet.EntityAttachS2CPacket;
import net.minecraft.client.network.packet.EntityTrackerUpdateS2CPacket;
import net.minecraft.client.network.packet.ScoreboardDisplayS2CPacket;
import net.minecraft.client.network.packet.ChunkLoadDistanceS2CPacket;
import net.minecraft.client.network.packet.ChunkRenderDistanceCenterS2CPacket;
import net.minecraft.client.network.packet.HeldItemChangeS2CPacket;
import net.minecraft.client.network.packet.SetCameraEntityS2CPacket;
import net.minecraft.client.network.packet.WorldBorderS2CPacket;
import net.minecraft.client.network.packet.SelectAdvancementTabS2CPacket;
import net.minecraft.client.network.packet.EntitySetHeadYawS2CPacket;
import net.minecraft.client.network.packet.PlayerRespawnS2CPacket;
import net.minecraft.client.network.packet.ResourcePackSendS2CPacket;
import net.minecraft.client.network.packet.RemoveEntityEffectS2CPacket;
import net.minecraft.client.network.packet.EntitiesDestroyS2CPacket;
import net.minecraft.client.network.packet.UnlockRecipesS2CPacket;
import net.minecraft.client.network.packet.PlayerPositionLookS2CPacket;
import net.minecraft.client.network.packet.LookAtS2CPacket;
import net.minecraft.client.network.packet.PlayerListS2CPacket;
import net.minecraft.client.network.packet.CombatEventS2CPacket;
import net.minecraft.client.network.packet.PlayerAbilitiesS2CPacket;
import net.minecraft.client.network.packet.CraftResponseS2CPacket;
import net.minecraft.client.network.packet.SignEditorOpenS2CPacket;
import net.minecraft.client.network.packet.OpenContainerPacket;
import net.minecraft.client.network.packet.OpenWrittenBookS2CPacket;
import net.minecraft.client.network.packet.VehicleMoveS2CPacket;
import net.minecraft.client.network.packet.EntityS2CPacket;
import net.minecraft.client.network.packet.SetTradeOffersPacket;
import net.minecraft.client.network.packet.MapUpdateS2CPacket;
import net.minecraft.client.network.packet.GameJoinS2CPacket;
import net.minecraft.client.network.packet.LightUpdateS2CPacket;
import net.minecraft.client.network.packet.ParticleS2CPacket;
import net.minecraft.client.network.packet.WorldEventS2CPacket;
import net.minecraft.client.network.packet.ChunkDataS2CPacket;
import net.minecraft.client.network.packet.KeepAliveS2CPacket;
import net.minecraft.client.network.packet.GuiOpenS2CPacket;
import net.minecraft.client.network.packet.GameStateChangeS2CPacket;
import net.minecraft.client.network.packet.UnloadChunkS2CPacket;
import net.minecraft.client.network.packet.ExplosionS2CPacket;
import net.minecraft.client.network.packet.EntityStatusS2CPacket;
import net.minecraft.client.network.packet.DisconnectS2CPacket;
import net.minecraft.client.network.packet.PlaySoundIdS2CPacket;
import net.minecraft.client.network.packet.CustomPayloadS2CPacket;
import net.minecraft.client.network.packet.CooldownUpdateS2CPacket;
import net.minecraft.client.network.packet.GuiSlotUpdateS2CPacket;
import net.minecraft.client.network.packet.GuiUpdateS2CPacket;
import net.minecraft.client.network.packet.InventoryS2CPacket;
import net.minecraft.client.network.packet.GuiCloseS2CPacket;
import net.minecraft.client.network.packet.ConfirmGuiActionS2CPacket;
import net.minecraft.client.network.packet.CommandTreeS2CPacket;
import net.minecraft.client.network.packet.CommandSuggestionsS2CPacket;
import net.minecraft.client.network.packet.ChunkDeltaUpdateS2CPacket;
import net.minecraft.client.network.packet.ChatMessageS2CPacket;
import net.minecraft.client.network.packet.DifficultyS2CPacket;
import net.minecraft.client.network.packet.BossBarS2CPacket;
import net.minecraft.client.network.packet.BlockUpdateS2CPacket;
import net.minecraft.client.network.packet.BlockActionS2CPacket;
import net.minecraft.client.network.packet.BlockEntityUpdateS2CPacket;
import net.minecraft.client.network.packet.BlockBreakingProgressS2CPacket;
import net.minecraft.client.network.packet.StatisticsS2CPacket;
import net.minecraft.client.network.packet.EntityAnimationS2CPacket;
import net.minecraft.client.network.packet.PlayerSpawnS2CPacket;
import net.minecraft.client.network.packet.PaintingSpawnS2CPacket;
import net.minecraft.client.network.packet.MobSpawnS2CPacket;
import net.minecraft.client.network.packet.EntitySpawnGlobalS2CPacket;
import net.minecraft.client.network.packet.ExperienceOrbSpawnS2CPacket;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.server.network.packet.HandshakeC2SPacket;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.common.collect.BiMap;
import java.util.Map;

public enum NetworkState
{
    HANDSHAKING(-1) {
        {
            this.addPacket(NetworkSide.SERVER, HandshakeC2SPacket.class);
        }
    }, 
    PLAY(0) {
        {
            this.addPacket(NetworkSide.CLIENT, EntitySpawnS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ExperienceOrbSpawnS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntitySpawnGlobalS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, MobSpawnS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PaintingSpawnS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlayerSpawnS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityAnimationS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, StatisticsS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, BlockBreakingProgressS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, BlockEntityUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, BlockActionS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, BlockUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, BossBarS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, DifficultyS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ChatMessageS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ChunkDeltaUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, CommandSuggestionsS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, CommandTreeS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ConfirmGuiActionS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, GuiCloseS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, InventoryS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, GuiUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, GuiSlotUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, CooldownUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, CustomPayloadS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlaySoundIdS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, DisconnectS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityStatusS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ExplosionS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, UnloadChunkS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, GameStateChangeS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, GuiOpenS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, KeepAliveS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ChunkDataS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, WorldEventS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ParticleS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, LightUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, GameJoinS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, MapUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, SetTradeOffersPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityS2CPacket.MoveRelative.class);
            this.addPacket(NetworkSide.CLIENT, EntityS2CPacket.RotateAndMoveRelative.class);
            this.addPacket(NetworkSide.CLIENT, EntityS2CPacket.Rotate.class);
            this.addPacket(NetworkSide.CLIENT, EntityS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, VehicleMoveS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, OpenWrittenBookS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, OpenContainerPacket.class);
            this.addPacket(NetworkSide.CLIENT, SignEditorOpenS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, CraftResponseS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlayerAbilitiesS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, CombatEventS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlayerListS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, LookAtS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlayerPositionLookS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, UnlockRecipesS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntitiesDestroyS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, RemoveEntityEffectS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ResourcePackSendS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlayerRespawnS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntitySetHeadYawS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, SelectAdvancementTabS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, WorldBorderS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, SetCameraEntityS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, HeldItemChangeS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ChunkRenderDistanceCenterS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ChunkLoadDistanceS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ScoreboardDisplayS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityTrackerUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityAttachS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityVelocityUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityEquipmentUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ExperienceBarUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, HealthUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ScoreboardObjectiveUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityPassengersSetS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, TeamS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ScoreboardPlayerUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlayerSpawnPositionS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, WorldTimeUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, TitleS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlaySoundFromEntityS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlaySoundS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, StopSoundS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, PlayerListHeaderS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, TagQueryResponseS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, ItemPickupAnimationS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityPositionS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, AdvancementUpdateS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityAttributesS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, EntityPotionEffectS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, SynchronizeRecipesS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, SynchronizeTagsS2CPacket.class);
            this.addPacket(NetworkSide.SERVER, TeleportConfirmC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, QueryBlockNbtC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateDifficultyC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, ChatMessageC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, ClientStatusC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, ClientSettingsC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, RequestCommandCompletionsC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, GuiActionConfirmC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, ButtonClickC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, ClickWindowC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, GuiCloseC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, CustomPayloadC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, BookUpdateC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, QueryEntityNbtC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, PlayerInteractEntityC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, KeepAliveC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateDifficultyLockC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, PlayerMoveC2SPacket.PositionOnly.class);
            this.addPacket(NetworkSide.SERVER, PlayerMoveC2SPacket.Both.class);
            this.addPacket(NetworkSide.SERVER, PlayerMoveC2SPacket.LookOnly.class);
            this.addPacket(NetworkSide.SERVER, PlayerMoveC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, VehicleMoveC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, BoatPaddleStateC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, PickFromInventoryC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, CraftRequestC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdatePlayerAbilitiesC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, PlayerActionC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, ClientCommandC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, PlayerInputC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, RecipeBookDataC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, RenameItemC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, ResourcePackStatusC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, AdvancementTabC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, SelectVillagerTradeC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateBeaconC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateSelectedSlotC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateCommandBlockC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateCommandBlockMinecartC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, CreativeInventoryActionC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateJigsawC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateStructureBlockC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, UpdateSignC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, HandSwingC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, SpectatorTeleportC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, PlayerInteractBlockC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, PlayerInteractItemC2SPacket.class);
        }
    }, 
    STATUS(1) {
        {
            this.addPacket(NetworkSide.SERVER, QueryRequestC2SPacket.class);
            this.addPacket(NetworkSide.CLIENT, QueryResponseS2CPacket.class);
            this.addPacket(NetworkSide.SERVER, QueryPingC2SPacket.class);
            this.addPacket(NetworkSide.CLIENT, QueryPongS2CPacket.class);
        }
    }, 
    LOGIN(2) {
        {
            this.addPacket(NetworkSide.CLIENT, LoginDisconnectS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, LoginHelloS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, LoginSuccessS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, LoginCompressionS2CPacket.class);
            this.addPacket(NetworkSide.CLIENT, LoginQueryRequestS2CPacket.class);
            this.addPacket(NetworkSide.SERVER, LoginHelloC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, LoginKeyC2SPacket.class);
            this.addPacket(NetworkSide.SERVER, LoginQueryResponseC2SPacket.class);
        }
    };
    
    private static final NetworkState[] STATES;
    private static final Map<Class<? extends Packet<?>>, NetworkState> HANDLER_STATE_MAP;
    private final int id;
    private final Map<NetworkSide, BiMap<Integer, Class<? extends Packet<?>>>> packetHandlerMap;
    
    private NetworkState(final int integer1) {
        this.packetHandlerMap = Maps.newEnumMap(NetworkSide.class);
        this.id = integer1;
    }
    
    protected NetworkState addPacket(final NetworkSide receivingSide, final Class<? extends Packet<?>> packetClass) {
        BiMap<Integer, Class<? extends Packet<?>>> biMap3 = this.packetHandlerMap.get(receivingSide);
        if (biMap3 == null) {
            biMap3 = HashBiMap.create();
            this.packetHandlerMap.put(receivingSide, biMap3);
        }
        if (biMap3.containsValue(packetClass)) {
            final String string4 = receivingSide + " packet " + packetClass + " is already known to ID " + biMap3.inverse().get(packetClass);
            LogManager.getLogger().fatal(string4);
            throw new IllegalArgumentException(string4);
        }
        biMap3.put(biMap3.size(), packetClass);
        return this;
    }
    
    public Integer getPacketId(final NetworkSide side, final Packet<?> packet) throws Exception {
        return this.packetHandlerMap.get(side).inverse().get(packet.getClass());
    }
    
    @Nullable
    public Packet<?> getPacketHandler(final NetworkSide side, final int integer) throws IllegalAccessException, InstantiationException {
        final Class<? extends Packet<?>> class3 = this.packetHandlerMap.get(side).get(integer);
        if (class3 == null) {
            return null;
        }
        return class3.newInstance();
    }
    
    public int getId() {
        return this.id;
    }
    
    public static NetworkState byId(final int id) {
        if (id < -1 || id > 2) {
            return null;
        }
        return NetworkState.STATES[id + 1];
    }
    
    public static NetworkState getPacketHandlerState(final Packet<?> handler) {
        return NetworkState.HANDLER_STATE_MAP.get(handler.getClass());
    }
    
    static {
        STATES = new NetworkState[4];
        HANDLER_STATE_MAP = Maps.newHashMap();
        for (final NetworkState networkState2 : values()) {
            final int integer3 = networkState2.getId();
            if (integer3 < -1 || integer3 > 2) {
                throw new Error("Invalid protocol ID " + Integer.toString(integer3));
            }
            NetworkState.STATES[integer3 + 1] = networkState2;
            for (final NetworkSide networkSide5 : networkState2.packetHandlerMap.keySet()) {
                for (final Class<? extends Packet<?>> class7 : networkState2.packetHandlerMap.get(networkSide5).values()) {
                    if (NetworkState.HANDLER_STATE_MAP.containsKey(class7) && NetworkState.HANDLER_STATE_MAP.get(class7) != networkState2) {
                        throw new Error("Packet " + class7 + " is already assigned to protocol " + NetworkState.HANDLER_STATE_MAP.get(class7) + " - can't reassign to " + networkState2);
                    }
                    try {
                        class7.newInstance();
                    }
                    catch (Throwable throwable8) {
                        throw new Error("Packet " + class7 + " fails instantiation checks! " + class7);
                    }
                    NetworkState.HANDLER_STATE_MAP.put(class7, networkState2);
                }
            }
        }
    }
}
