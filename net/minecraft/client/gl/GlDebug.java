package net.minecraft.client.gl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import net.minecraft.client.util.GlAllocationUtils;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GLDebugMessageARBCallback;
import org.lwjgl.opengl.GLDebugMessageARBCallbackI;
import org.lwjgl.opengl.ARBDebugOutput;
import java.util.function.Consumer;
import com.mojang.blaze3d.platform.GLX;
import net.minecraft.client.util.UntrackMemoryUtil;
import org.lwjgl.opengl.GLDebugMessageCallbackI;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLDebugMessageCallback;
import java.util.List;
import java.util.Map;
import com.google.common.base.Joiner;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import org.apache.logging.log4j.Logger;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GlDebug
{
    private static final Logger LOGGER;
    protected static final ByteBuffer a;
    protected static final FloatBuffer b;
    protected static final IntBuffer c;
    private static final Joiner NEWLINE_JOINER;
    private static final Joiner SEMICOLON_JOINER;
    private static final Map<Integer, String> CONSTANTS;
    private static final List<Integer> KHR_VERBOSITY_LEVELS;
    private static final List<Integer> ARB_VERBOSITY_LEVELS;
    private static final Map<String, List<String>> j;
    
    private static String unknown(final int opcode) {
        return "Unknown (0x" + Integer.toHexString(opcode).toUpperCase() + ")";
    }
    
    private static String getSource(final int opcode) {
        switch (opcode) {
            case 33350: {
                return "API";
            }
            case 33351: {
                return "WINDOW SYSTEM";
            }
            case 33352: {
                return "SHADER COMPILER";
            }
            case 33353: {
                return "THIRD PARTY";
            }
            case 33354: {
                return "APPLICATION";
            }
            case 33355: {
                return "OTHER";
            }
            default: {
                return unknown(opcode);
            }
        }
    }
    
    private static String getType(final int opcode) {
        switch (opcode) {
            case 33356: {
                return "ERROR";
            }
            case 33357: {
                return "DEPRECATED BEHAVIOR";
            }
            case 33358: {
                return "UNDEFINED BEHAVIOR";
            }
            case 33359: {
                return "PORTABILITY";
            }
            case 33360: {
                return "PERFORMANCE";
            }
            case 33361: {
                return "OTHER";
            }
            case 33384: {
                return "MARKER";
            }
            default: {
                return unknown(opcode);
            }
        }
    }
    
    private static String getSeverity(final int opcode) {
        switch (opcode) {
            case 37190: {
                return "HIGH";
            }
            case 37191: {
                return "MEDIUM";
            }
            case 37192: {
                return "LOW";
            }
            case 33387: {
                return "NOTIFICATION";
            }
            default: {
                return unknown(opcode);
            }
        }
    }
    
    private static void info(final int source, final int type, final int id, final int severity, final int integer5, final long long6, final long long8) {
        GlDebug.LOGGER.info("OpenGL debug message, id={}, source={}, type={}, severity={}, message={}", id, getSource(source), getType(type), getSeverity(severity), GLDebugMessageCallback.getMessage(integer5, long6));
    }
    
    private static void registerConstant(final int constant, final String description) {
        GlDebug.CONSTANTS.merge(constant, description, (string1, string2) -> string1 + "/" + string2);
    }
    
    public static void enableDebug(final int verbosity, final boolean boolean2) {
        if (verbosity <= 0) {
            return;
        }
        final GLCapabilities gLCapabilities3 = GL.getCapabilities();
        if (gLCapabilities3.GL_KHR_debug) {
            GL11.glEnable(37600);
            if (boolean2) {
                GL11.glEnable(33346);
            }
            for (int integer4 = 0; integer4 < GlDebug.KHR_VERBOSITY_LEVELS.size(); ++integer4) {
                final boolean boolean3 = integer4 < verbosity;
                KHRDebug.glDebugMessageControl(4352, 4352, (int)GlDebug.KHR_VERBOSITY_LEVELS.get(integer4), (int[])null, boolean3);
            }
            KHRDebug.glDebugMessageCallback((GLDebugMessageCallbackI)GLX.<GLDebugMessageCallbackI>make((GLDebugMessageCallbackI)GLDebugMessageCallback.create(GlDebug::info), (Consumer<GLDebugMessageCallbackI>)UntrackMemoryUtil::untrack), 0L);
        }
        else if (gLCapabilities3.GL_ARB_debug_output) {
            if (boolean2) {
                GL11.glEnable(33346);
            }
            for (int integer4 = 0; integer4 < GlDebug.ARB_VERBOSITY_LEVELS.size(); ++integer4) {
                final boolean boolean3 = integer4 < verbosity;
                ARBDebugOutput.glDebugMessageControlARB(4352, 4352, (int)GlDebug.ARB_VERBOSITY_LEVELS.get(integer4), (int[])null, boolean3);
            }
            ARBDebugOutput.glDebugMessageCallbackARB((GLDebugMessageARBCallbackI)GLX.<GLDebugMessageARBCallbackI>make((GLDebugMessageARBCallbackI)GLDebugMessageARBCallback.create(GlDebug::info), (Consumer<GLDebugMessageARBCallbackI>)UntrackMemoryUtil::untrack), 0L);
        }
    }
    
    static {
        LOGGER = LogManager.getLogger();
        a = GlAllocationUtils.allocateByteBuffer(64);
        b = GlDebug.a.asFloatBuffer();
        c = GlDebug.a.asIntBuffer();
        NEWLINE_JOINER = Joiner.on('\n');
        SEMICOLON_JOINER = Joiner.on("; ");
        CONSTANTS = Maps.newHashMap();
        KHR_VERBOSITY_LEVELS = ImmutableList.<Integer>of(37190, 37191, 37192, 33387);
        ARB_VERBOSITY_LEVELS = ImmutableList.<Integer>of(37190, 37191, 37192);
        registerConstant(256, "GL11.GL_ACCUM");
        registerConstant(257, "GL11.GL_LOAD");
        registerConstant(258, "GL11.GL_RETURN");
        registerConstant(259, "GL11.GL_MULT");
        registerConstant(260, "GL11.GL_ADD");
        registerConstant(512, "GL11.GL_NEVER");
        registerConstant(513, "GL11.GL_LESS");
        registerConstant(514, "GL11.GL_EQUAL");
        registerConstant(515, "GL11.GL_LEQUAL");
        registerConstant(516, "GL11.GL_GREATER");
        registerConstant(517, "GL11.GL_NOTEQUAL");
        registerConstant(518, "GL11.GL_GEQUAL");
        registerConstant(519, "GL11.GL_ALWAYS");
        registerConstant(0, "GL11.GL_POINTS");
        registerConstant(1, "GL11.GL_LINES");
        registerConstant(2, "GL11.GL_LINE_LOOP");
        registerConstant(3, "GL11.GL_LINE_STRIP");
        registerConstant(4, "GL11.GL_TRIANGLES");
        registerConstant(5, "GL11.GL_TRIANGLE_STRIP");
        registerConstant(6, "GL11.GL_TRIANGLE_FAN");
        registerConstant(7, "GL11.GL_QUADS");
        registerConstant(8, "GL11.GL_QUAD_STRIP");
        registerConstant(9, "GL11.GL_POLYGON");
        registerConstant(0, "GL11.GL_ZERO");
        registerConstant(1, "GL11.GL_ONE");
        registerConstant(768, "GL11.GL_SRC_COLOR");
        registerConstant(769, "GL11.GL_ONE_MINUS_SRC_COLOR");
        registerConstant(770, "GL11.GL_SRC_ALPHA");
        registerConstant(771, "GL11.GL_ONE_MINUS_SRC_ALPHA");
        registerConstant(772, "GL11.GL_DST_ALPHA");
        registerConstant(773, "GL11.GL_ONE_MINUS_DST_ALPHA");
        registerConstant(774, "GL11.GL_DST_COLOR");
        registerConstant(775, "GL11.GL_ONE_MINUS_DST_COLOR");
        registerConstant(776, "GL11.GL_SRC_ALPHA_SATURATE");
        registerConstant(32769, "GL14.GL_CONSTANT_COLOR");
        registerConstant(32770, "GL14.GL_ONE_MINUS_CONSTANT_COLOR");
        registerConstant(32771, "GL14.GL_CONSTANT_ALPHA");
        registerConstant(32772, "GL14.GL_ONE_MINUS_CONSTANT_ALPHA");
        registerConstant(1, "GL11.GL_TRUE");
        registerConstant(0, "GL11.GL_FALSE");
        registerConstant(12288, "GL11.GL_CLIP_PLANE0");
        registerConstant(12289, "GL11.GL_CLIP_PLANE1");
        registerConstant(12290, "GL11.GL_CLIP_PLANE2");
        registerConstant(12291, "GL11.GL_CLIP_PLANE3");
        registerConstant(12292, "GL11.GL_CLIP_PLANE4");
        registerConstant(12293, "GL11.GL_CLIP_PLANE5");
        registerConstant(5120, "GL11.GL_BYTE");
        registerConstant(5121, "GL11.GL_UNSIGNED_BYTE");
        registerConstant(5122, "GL11.GL_SHORT");
        registerConstant(5123, "GL11.GL_UNSIGNED_SHORT");
        registerConstant(5124, "GL11.GL_INT");
        registerConstant(5125, "GL11.GL_UNSIGNED_INT");
        registerConstant(5126, "GL11.GL_FLOAT");
        registerConstant(5127, "GL11.GL_2_BYTES");
        registerConstant(5128, "GL11.GL_3_BYTES");
        registerConstant(5129, "GL11.GL_4_BYTES");
        registerConstant(5130, "GL11.GL_DOUBLE");
        registerConstant(0, "GL11.GL_NONE");
        registerConstant(1024, "GL11.GL_FRONT_LEFT");
        registerConstant(1025, "GL11.GL_FRONT_RIGHT");
        registerConstant(1026, "GL11.GL_BACK_LEFT");
        registerConstant(1027, "GL11.GL_BACK_RIGHT");
        registerConstant(1028, "GL11.GL_FRONT");
        registerConstant(1029, "GL11.GL_BACK");
        registerConstant(1030, "GL11.GL_LEFT");
        registerConstant(1031, "GL11.GL_RIGHT");
        registerConstant(1032, "GL11.GL_FRONT_AND_BACK");
        registerConstant(1033, "GL11.GL_AUX0");
        registerConstant(1034, "GL11.GL_AUX1");
        registerConstant(1035, "GL11.GL_AUX2");
        registerConstant(1036, "GL11.GL_AUX3");
        registerConstant(0, "GL11.GL_NO_ERROR");
        registerConstant(1280, "GL11.GL_INVALID_ENUM");
        registerConstant(1281, "GL11.GL_INVALID_VALUE");
        registerConstant(1282, "GL11.GL_INVALID_OPERATION");
        registerConstant(1283, "GL11.GL_STACK_OVERFLOW");
        registerConstant(1284, "GL11.GL_STACK_UNDERFLOW");
        registerConstant(1285, "GL11.GL_OUT_OF_MEMORY");
        registerConstant(1536, "GL11.GL_2D");
        registerConstant(1537, "GL11.GL_3D");
        registerConstant(1538, "GL11.GL_3D_COLOR");
        registerConstant(1539, "GL11.GL_3D_COLOR_TEXTURE");
        registerConstant(1540, "GL11.GL_4D_COLOR_TEXTURE");
        registerConstant(1792, "GL11.GL_PASS_THROUGH_TOKEN");
        registerConstant(1793, "GL11.GL_POINT_TOKEN");
        registerConstant(1794, "GL11.GL_LINE_TOKEN");
        registerConstant(1795, "GL11.GL_POLYGON_TOKEN");
        registerConstant(1796, "GL11.GL_BITMAP_TOKEN");
        registerConstant(1797, "GL11.GL_DRAW_PIXEL_TOKEN");
        registerConstant(1798, "GL11.GL_COPY_PIXEL_TOKEN");
        registerConstant(1799, "GL11.GL_LINE_RESET_TOKEN");
        registerConstant(2048, "GL11.GL_EXP");
        registerConstant(2049, "GL11.GL_EXP2");
        registerConstant(2304, "GL11.GL_CW");
        registerConstant(2305, "GL11.GL_CCW");
        registerConstant(2560, "GL11.GL_COEFF");
        registerConstant(2561, "GL11.GL_ORDER");
        registerConstant(2562, "GL11.GL_DOMAIN");
        registerConstant(2816, "GL11.GL_CURRENT_COLOR");
        registerConstant(2817, "GL11.GL_CURRENT_INDEX");
        registerConstant(2818, "GL11.GL_CURRENT_NORMAL");
        registerConstant(2819, "GL11.GL_CURRENT_TEXTURE_COORDS");
        registerConstant(2820, "GL11.GL_CURRENT_RASTER_COLOR");
        registerConstant(2821, "GL11.GL_CURRENT_RASTER_INDEX");
        registerConstant(2822, "GL11.GL_CURRENT_RASTER_TEXTURE_COORDS");
        registerConstant(2823, "GL11.GL_CURRENT_RASTER_POSITION");
        registerConstant(2824, "GL11.GL_CURRENT_RASTER_POSITION_VALID");
        registerConstant(2825, "GL11.GL_CURRENT_RASTER_DISTANCE");
        registerConstant(2832, "GL11.GL_POINT_SMOOTH");
        registerConstant(2833, "GL11.GL_POINT_SIZE");
        registerConstant(2834, "GL11.GL_POINT_SIZE_RANGE");
        registerConstant(2835, "GL11.GL_POINT_SIZE_GRANULARITY");
        registerConstant(2848, "GL11.GL_LINE_SMOOTH");
        registerConstant(2849, "GL11.GL_LINE_WIDTH");
        registerConstant(2850, "GL11.GL_LINE_WIDTH_RANGE");
        registerConstant(2851, "GL11.GL_LINE_WIDTH_GRANULARITY");
        registerConstant(2852, "GL11.GL_LINE_STIPPLE");
        registerConstant(2853, "GL11.GL_LINE_STIPPLE_PATTERN");
        registerConstant(2854, "GL11.GL_LINE_STIPPLE_REPEAT");
        registerConstant(2864, "GL11.GL_LIST_MODE");
        registerConstant(2865, "GL11.GL_MAX_LIST_NESTING");
        registerConstant(2866, "GL11.GL_LIST_BASE");
        registerConstant(2867, "GL11.GL_LIST_INDEX");
        registerConstant(2880, "GL11.GL_POLYGON_MODE");
        registerConstant(2881, "GL11.GL_POLYGON_SMOOTH");
        registerConstant(2882, "GL11.GL_POLYGON_STIPPLE");
        registerConstant(2883, "GL11.GL_EDGE_FLAG");
        registerConstant(2884, "GL11.GL_CULL_FACE");
        registerConstant(2885, "GL11.GL_CULL_FACE_MODE");
        registerConstant(2886, "GL11.GL_FRONT_FACE");
        registerConstant(2896, "GL11.GL_LIGHTING");
        registerConstant(2897, "GL11.GL_LIGHT_MODEL_LOCAL_VIEWER");
        registerConstant(2898, "GL11.GL_LIGHT_MODEL_TWO_SIDE");
        registerConstant(2899, "GL11.GL_LIGHT_MODEL_AMBIENT");
        registerConstant(2900, "GL11.GL_SHADE_MODEL");
        registerConstant(2901, "GL11.GL_COLOR_MATERIAL_FACE");
        registerConstant(2902, "GL11.GL_COLOR_MATERIAL_PARAMETER");
        registerConstant(2903, "GL11.GL_COLOR_MATERIAL");
        registerConstant(2912, "GL11.GL_FOG");
        registerConstant(2913, "GL11.GL_FOG_INDEX");
        registerConstant(2914, "GL11.GL_FOG_DENSITY");
        registerConstant(2915, "GL11.GL_FOG_START");
        registerConstant(2916, "GL11.GL_FOG_END");
        registerConstant(2917, "GL11.GL_FOG_MODE");
        registerConstant(2918, "GL11.GL_FOG_COLOR");
        registerConstant(2928, "GL11.GL_DEPTH_RANGE");
        registerConstant(2929, "GL11.GL_DEPTH_TEST");
        registerConstant(2930, "GL11.GL_DEPTH_WRITEMASK");
        registerConstant(2931, "GL11.GL_DEPTH_CLEAR_VALUE");
        registerConstant(2932, "GL11.GL_DEPTH_FUNC");
        registerConstant(2944, "GL11.GL_ACCUM_CLEAR_VALUE");
        registerConstant(2960, "GL11.GL_STENCIL_TEST");
        registerConstant(2961, "GL11.GL_STENCIL_CLEAR_VALUE");
        registerConstant(2962, "GL11.GL_STENCIL_FUNC");
        registerConstant(2963, "GL11.GL_STENCIL_VALUE_MASK");
        registerConstant(2964, "GL11.GL_STENCIL_FAIL");
        registerConstant(2965, "GL11.GL_STENCIL_PASS_DEPTH_FAIL");
        registerConstant(2966, "GL11.GL_STENCIL_PASS_DEPTH_PASS");
        registerConstant(2967, "GL11.GL_STENCIL_REF");
        registerConstant(2968, "GL11.GL_STENCIL_WRITEMASK");
        registerConstant(2976, "GL11.GL_MATRIX_MODE");
        registerConstant(2977, "GL11.GL_NORMALIZE");
        registerConstant(2978, "GL11.GL_VIEWPORT");
        registerConstant(2979, "GL11.GL_MODELVIEW_STACK_DEPTH");
        registerConstant(2980, "GL11.GL_PROJECTION_STACK_DEPTH");
        registerConstant(2981, "GL11.GL_TEXTURE_STACK_DEPTH");
        registerConstant(2982, "GL11.GL_MODELVIEW_MATRIX");
        registerConstant(2983, "GL11.GL_PROJECTION_MATRIX");
        registerConstant(2984, "GL11.GL_TEXTURE_MATRIX");
        registerConstant(2992, "GL11.GL_ATTRIB_STACK_DEPTH");
        registerConstant(2993, "GL11.GL_CLIENT_ATTRIB_STACK_DEPTH");
        registerConstant(3008, "GL11.GL_ALPHA_TEST");
        registerConstant(3009, "GL11.GL_ALPHA_TEST_FUNC");
        registerConstant(3010, "GL11.GL_ALPHA_TEST_REF");
        registerConstant(3024, "GL11.GL_DITHER");
        registerConstant(3040, "GL11.GL_BLEND_DST");
        registerConstant(3041, "GL11.GL_BLEND_SRC");
        registerConstant(3042, "GL11.GL_BLEND");
        registerConstant(3056, "GL11.GL_LOGIC_OP_MODE");
        registerConstant(3057, "GL11.GL_INDEX_LOGIC_OP");
        registerConstant(3058, "GL11.GL_COLOR_LOGIC_OP");
        registerConstant(3072, "GL11.GL_AUX_BUFFERS");
        registerConstant(3073, "GL11.GL_DRAW_BUFFER");
        registerConstant(3074, "GL11.GL_READ_BUFFER");
        registerConstant(3088, "GL11.GL_SCISSOR_BOX");
        registerConstant(3089, "GL11.GL_SCISSOR_TEST");
        registerConstant(3104, "GL11.GL_INDEX_CLEAR_VALUE");
        registerConstant(3105, "GL11.GL_INDEX_WRITEMASK");
        registerConstant(3106, "GL11.GL_COLOR_CLEAR_VALUE");
        registerConstant(3107, "GL11.GL_COLOR_WRITEMASK");
        registerConstant(3120, "GL11.GL_INDEX_MODE");
        registerConstant(3121, "GL11.GL_RGBA_MODE");
        registerConstant(3122, "GL11.GL_DOUBLEBUFFER");
        registerConstant(3123, "GL11.GL_STEREO");
        registerConstant(3136, "GL11.GL_RENDER_MODE");
        registerConstant(3152, "GL11.GL_PERSPECTIVE_CORRECTION_HINT");
        registerConstant(3153, "GL11.GL_POINT_SMOOTH_HINT");
        registerConstant(3154, "GL11.GL_LINE_SMOOTH_HINT");
        registerConstant(3155, "GL11.GL_POLYGON_SMOOTH_HINT");
        registerConstant(3156, "GL11.GL_FOG_HINT");
        registerConstant(3168, "GL11.GL_TEXTURE_GEN_S");
        registerConstant(3169, "GL11.GL_TEXTURE_GEN_T");
        registerConstant(3170, "GL11.GL_TEXTURE_GEN_R");
        registerConstant(3171, "GL11.GL_TEXTURE_GEN_Q");
        registerConstant(3184, "GL11.GL_PIXEL_MAP_I_TO_I");
        registerConstant(3185, "GL11.GL_PIXEL_MAP_S_TO_S");
        registerConstant(3186, "GL11.GL_PIXEL_MAP_I_TO_R");
        registerConstant(3187, "GL11.GL_PIXEL_MAP_I_TO_G");
        registerConstant(3188, "GL11.GL_PIXEL_MAP_I_TO_B");
        registerConstant(3189, "GL11.GL_PIXEL_MAP_I_TO_A");
        registerConstant(3190, "GL11.GL_PIXEL_MAP_R_TO_R");
        registerConstant(3191, "GL11.GL_PIXEL_MAP_G_TO_G");
        registerConstant(3192, "GL11.GL_PIXEL_MAP_B_TO_B");
        registerConstant(3193, "GL11.GL_PIXEL_MAP_A_TO_A");
        registerConstant(3248, "GL11.GL_PIXEL_MAP_I_TO_I_SIZE");
        registerConstant(3249, "GL11.GL_PIXEL_MAP_S_TO_S_SIZE");
        registerConstant(3250, "GL11.GL_PIXEL_MAP_I_TO_R_SIZE");
        registerConstant(3251, "GL11.GL_PIXEL_MAP_I_TO_G_SIZE");
        registerConstant(3252, "GL11.GL_PIXEL_MAP_I_TO_B_SIZE");
        registerConstant(3253, "GL11.GL_PIXEL_MAP_I_TO_A_SIZE");
        registerConstant(3254, "GL11.GL_PIXEL_MAP_R_TO_R_SIZE");
        registerConstant(3255, "GL11.GL_PIXEL_MAP_G_TO_G_SIZE");
        registerConstant(3256, "GL11.GL_PIXEL_MAP_B_TO_B_SIZE");
        registerConstant(3257, "GL11.GL_PIXEL_MAP_A_TO_A_SIZE");
        registerConstant(3312, "GL11.GL_UNPACK_SWAP_BYTES");
        registerConstant(3313, "GL11.GL_UNPACK_LSB_FIRST");
        registerConstant(3314, "GL11.GL_UNPACK_ROW_LENGTH");
        registerConstant(3315, "GL11.GL_UNPACK_SKIP_ROWS");
        registerConstant(3316, "GL11.GL_UNPACK_SKIP_PIXELS");
        registerConstant(3317, "GL11.GL_UNPACK_ALIGNMENT");
        registerConstant(3328, "GL11.GL_PACK_SWAP_BYTES");
        registerConstant(3329, "GL11.GL_PACK_LSB_FIRST");
        registerConstant(3330, "GL11.GL_PACK_ROW_LENGTH");
        registerConstant(3331, "GL11.GL_PACK_SKIP_ROWS");
        registerConstant(3332, "GL11.GL_PACK_SKIP_PIXELS");
        registerConstant(3333, "GL11.GL_PACK_ALIGNMENT");
        registerConstant(3344, "GL11.GL_MAP_COLOR");
        registerConstant(3345, "GL11.GL_MAP_STENCIL");
        registerConstant(3346, "GL11.GL_INDEX_SHIFT");
        registerConstant(3347, "GL11.GL_INDEX_OFFSET");
        registerConstant(3348, "GL11.GL_RED_SCALE");
        registerConstant(3349, "GL11.GL_RED_BIAS");
        registerConstant(3350, "GL11.GL_ZOOM_X");
        registerConstant(3351, "GL11.GL_ZOOM_Y");
        registerConstant(3352, "GL11.GL_GREEN_SCALE");
        registerConstant(3353, "GL11.GL_GREEN_BIAS");
        registerConstant(3354, "GL11.GL_BLUE_SCALE");
        registerConstant(3355, "GL11.GL_BLUE_BIAS");
        registerConstant(3356, "GL11.GL_ALPHA_SCALE");
        registerConstant(3357, "GL11.GL_ALPHA_BIAS");
        registerConstant(3358, "GL11.GL_DEPTH_SCALE");
        registerConstant(3359, "GL11.GL_DEPTH_BIAS");
        registerConstant(3376, "GL11.GL_MAX_EVAL_ORDER");
        registerConstant(3377, "GL11.GL_MAX_LIGHTS");
        registerConstant(3378, "GL11.GL_MAX_CLIP_PLANES");
        registerConstant(3379, "GL11.GL_MAX_TEXTURE_SIZE");
        registerConstant(3380, "GL11.GL_MAX_PIXEL_MAP_TABLE");
        registerConstant(3381, "GL11.GL_MAX_ATTRIB_STACK_DEPTH");
        registerConstant(3382, "GL11.GL_MAX_MODELVIEW_STACK_DEPTH");
        registerConstant(3383, "GL11.GL_MAX_NAME_STACK_DEPTH");
        registerConstant(3384, "GL11.GL_MAX_PROJECTION_STACK_DEPTH");
        registerConstant(3385, "GL11.GL_MAX_TEXTURE_STACK_DEPTH");
        registerConstant(3386, "GL11.GL_MAX_VIEWPORT_DIMS");
        registerConstant(3387, "GL11.GL_MAX_CLIENT_ATTRIB_STACK_DEPTH");
        registerConstant(3408, "GL11.GL_SUBPIXEL_BITS");
        registerConstant(3409, "GL11.GL_INDEX_BITS");
        registerConstant(3410, "GL11.GL_RED_BITS");
        registerConstant(3411, "GL11.GL_GREEN_BITS");
        registerConstant(3412, "GL11.GL_BLUE_BITS");
        registerConstant(3413, "GL11.GL_ALPHA_BITS");
        registerConstant(3414, "GL11.GL_DEPTH_BITS");
        registerConstant(3415, "GL11.GL_STENCIL_BITS");
        registerConstant(3416, "GL11.GL_ACCUM_RED_BITS");
        registerConstant(3417, "GL11.GL_ACCUM_GREEN_BITS");
        registerConstant(3418, "GL11.GL_ACCUM_BLUE_BITS");
        registerConstant(3419, "GL11.GL_ACCUM_ALPHA_BITS");
        registerConstant(3440, "GL11.GL_NAME_STACK_DEPTH");
        registerConstant(3456, "GL11.GL_AUTO_NORMAL");
        registerConstant(3472, "GL11.GL_MAP1_COLOR_4");
        registerConstant(3473, "GL11.GL_MAP1_INDEX");
        registerConstant(3474, "GL11.GL_MAP1_NORMAL");
        registerConstant(3475, "GL11.GL_MAP1_TEXTURE_COORD_1");
        registerConstant(3476, "GL11.GL_MAP1_TEXTURE_COORD_2");
        registerConstant(3477, "GL11.GL_MAP1_TEXTURE_COORD_3");
        registerConstant(3478, "GL11.GL_MAP1_TEXTURE_COORD_4");
        registerConstant(3479, "GL11.GL_MAP1_VERTEX_3");
        registerConstant(3480, "GL11.GL_MAP1_VERTEX_4");
        registerConstant(3504, "GL11.GL_MAP2_COLOR_4");
        registerConstant(3505, "GL11.GL_MAP2_INDEX");
        registerConstant(3506, "GL11.GL_MAP2_NORMAL");
        registerConstant(3507, "GL11.GL_MAP2_TEXTURE_COORD_1");
        registerConstant(3508, "GL11.GL_MAP2_TEXTURE_COORD_2");
        registerConstant(3509, "GL11.GL_MAP2_TEXTURE_COORD_3");
        registerConstant(3510, "GL11.GL_MAP2_TEXTURE_COORD_4");
        registerConstant(3511, "GL11.GL_MAP2_VERTEX_3");
        registerConstant(3512, "GL11.GL_MAP2_VERTEX_4");
        registerConstant(3536, "GL11.GL_MAP1_GRID_DOMAIN");
        registerConstant(3537, "GL11.GL_MAP1_GRID_SEGMENTS");
        registerConstant(3538, "GL11.GL_MAP2_GRID_DOMAIN");
        registerConstant(3539, "GL11.GL_MAP2_GRID_SEGMENTS");
        registerConstant(3552, "GL11.GL_TEXTURE_1D");
        registerConstant(3553, "GL11.GL_TEXTURE_2D");
        registerConstant(3568, "GL11.GL_FEEDBACK_BUFFER_POINTER");
        registerConstant(3569, "GL11.GL_FEEDBACK_BUFFER_SIZE");
        registerConstant(3570, "GL11.GL_FEEDBACK_BUFFER_TYPE");
        registerConstant(3571, "GL11.GL_SELECTION_BUFFER_POINTER");
        registerConstant(3572, "GL11.GL_SELECTION_BUFFER_SIZE");
        registerConstant(4096, "GL11.GL_TEXTURE_WIDTH");
        registerConstant(4097, "GL11.GL_TEXTURE_HEIGHT");
        registerConstant(4099, "GL11.GL_TEXTURE_INTERNAL_FORMAT");
        registerConstant(4100, "GL11.GL_TEXTURE_BORDER_COLOR");
        registerConstant(4101, "GL11.GL_TEXTURE_BORDER");
        registerConstant(4352, "GL11.GL_DONT_CARE");
        registerConstant(4353, "GL11.GL_FASTEST");
        registerConstant(4354, "GL11.GL_NICEST");
        registerConstant(16384, "GL11.GL_LIGHT0");
        registerConstant(16385, "GL11.GL_LIGHT1");
        registerConstant(16386, "GL11.GL_LIGHT2");
        registerConstant(16387, "GL11.GL_LIGHT3");
        registerConstant(16388, "GL11.GL_LIGHT4");
        registerConstant(16389, "GL11.GL_LIGHT5");
        registerConstant(16390, "GL11.GL_LIGHT6");
        registerConstant(16391, "GL11.GL_LIGHT7");
        registerConstant(4608, "GL11.GL_AMBIENT");
        registerConstant(4609, "GL11.GL_DIFFUSE");
        registerConstant(4610, "GL11.GL_SPECULAR");
        registerConstant(4611, "GL11.GL_POSITION");
        registerConstant(4612, "GL11.GL_SPOT_DIRECTION");
        registerConstant(4613, "GL11.GL_SPOT_EXPONENT");
        registerConstant(4614, "GL11.GL_SPOT_CUTOFF");
        registerConstant(4615, "GL11.GL_CONSTANT_ATTENUATION");
        registerConstant(4616, "GL11.GL_LINEAR_ATTENUATION");
        registerConstant(4617, "GL11.GL_QUADRATIC_ATTENUATION");
        registerConstant(4864, "GL11.GL_COMPILE");
        registerConstant(4865, "GL11.GL_COMPILE_AND_EXECUTE");
        registerConstant(5376, "GL11.GL_CLEAR");
        registerConstant(5377, "GL11.GL_AND");
        registerConstant(5378, "GL11.GL_AND_REVERSE");
        registerConstant(5379, "GL11.GL_COPY");
        registerConstant(5380, "GL11.GL_AND_INVERTED");
        registerConstant(5381, "GL11.GL_NOOP");
        registerConstant(5382, "GL11.GL_XOR");
        registerConstant(5383, "GL11.GL_OR");
        registerConstant(5384, "GL11.GL_NOR");
        registerConstant(5385, "GL11.GL_EQUIV");
        registerConstant(5386, "GL11.GL_INVERT");
        registerConstant(5387, "GL11.GL_OR_REVERSE");
        registerConstant(5388, "GL11.GL_COPY_INVERTED");
        registerConstant(5389, "GL11.GL_OR_INVERTED");
        registerConstant(5390, "GL11.GL_NAND");
        registerConstant(5391, "GL11.GL_SET");
        registerConstant(5632, "GL11.GL_EMISSION");
        registerConstant(5633, "GL11.GL_SHININESS");
        registerConstant(5634, "GL11.GL_AMBIENT_AND_DIFFUSE");
        registerConstant(5635, "GL11.GL_COLOR_INDEXES");
        registerConstant(5888, "GL11.GL_MODELVIEW");
        registerConstant(5889, "GL11.GL_PROJECTION");
        registerConstant(5890, "GL11.GL_TEXTURE");
        registerConstant(6144, "GL11.GL_COLOR");
        registerConstant(6145, "GL11.GL_DEPTH");
        registerConstant(6146, "GL11.GL_STENCIL");
        registerConstant(6400, "GL11.GL_COLOR_INDEX");
        registerConstant(6401, "GL11.GL_STENCIL_INDEX");
        registerConstant(6402, "GL11.GL_DEPTH_COMPONENT");
        registerConstant(6403, "GL11.GL_RED");
        registerConstant(6404, "GL11.GL_GREEN");
        registerConstant(6405, "GL11.GL_BLUE");
        registerConstant(6406, "GL11.GL_ALPHA");
        registerConstant(6407, "GL11.GL_RGB");
        registerConstant(6408, "GL11.GL_RGBA");
        registerConstant(6409, "GL11.GL_LUMINANCE");
        registerConstant(6410, "GL11.GL_LUMINANCE_ALPHA");
        registerConstant(6656, "GL11.GL_BITMAP");
        registerConstant(6912, "GL11.GL_POINT");
        registerConstant(6913, "GL11.GL_LINE");
        registerConstant(6914, "GL11.GL_FILL");
        registerConstant(7168, "GL11.GL_RENDER");
        registerConstant(7169, "GL11.GL_FEEDBACK");
        registerConstant(7170, "GL11.GL_SELECT");
        registerConstant(7424, "GL11.GL_FLAT");
        registerConstant(7425, "GL11.GL_SMOOTH");
        registerConstant(7680, "GL11.GL_KEEP");
        registerConstant(7681, "GL11.GL_REPLACE");
        registerConstant(7682, "GL11.GL_INCR");
        registerConstant(7683, "GL11.GL_DECR");
        registerConstant(7936, "GL11.GL_VENDOR");
        registerConstant(7937, "GL11.GL_RENDERER");
        registerConstant(7938, "GL11.GL_VERSION");
        registerConstant(7939, "GL11.GL_EXTENSIONS");
        registerConstant(8192, "GL11.GL_S");
        registerConstant(8193, "GL11.GL_T");
        registerConstant(8194, "GL11.GL_R");
        registerConstant(8195, "GL11.GL_Q");
        registerConstant(8448, "GL11.GL_MODULATE");
        registerConstant(8449, "GL11.GL_DECAL");
        registerConstant(8704, "GL11.GL_TEXTURE_ENV_MODE");
        registerConstant(8705, "GL11.GL_TEXTURE_ENV_COLOR");
        registerConstant(8960, "GL11.GL_TEXTURE_ENV");
        registerConstant(9216, "GL11.GL_EYE_LINEAR");
        registerConstant(9217, "GL11.GL_OBJECT_LINEAR");
        registerConstant(9218, "GL11.GL_SPHERE_MAP");
        registerConstant(9472, "GL11.GL_TEXTURE_GEN_MODE");
        registerConstant(9473, "GL11.GL_OBJECT_PLANE");
        registerConstant(9474, "GL11.GL_EYE_PLANE");
        registerConstant(9728, "GL11.GL_NEAREST");
        registerConstant(9729, "GL11.GL_LINEAR");
        registerConstant(9984, "GL11.GL_NEAREST_MIPMAP_NEAREST");
        registerConstant(9985, "GL11.GL_LINEAR_MIPMAP_NEAREST");
        registerConstant(9986, "GL11.GL_NEAREST_MIPMAP_LINEAR");
        registerConstant(9987, "GL11.GL_LINEAR_MIPMAP_LINEAR");
        registerConstant(10240, "GL11.GL_TEXTURE_MAG_FILTER");
        registerConstant(10241, "GL11.GL_TEXTURE_MIN_FILTER");
        registerConstant(10242, "GL11.GL_TEXTURE_WRAP_S");
        registerConstant(10243, "GL11.GL_TEXTURE_WRAP_T");
        registerConstant(10496, "GL11.GL_CLAMP");
        registerConstant(10497, "GL11.GL_REPEAT");
        registerConstant(-1, "GL11.GL_ALL_CLIENT_ATTRIB_BITS");
        registerConstant(32824, "GL11.GL_POLYGON_OFFSET_FACTOR");
        registerConstant(10752, "GL11.GL_POLYGON_OFFSET_UNITS");
        registerConstant(10753, "GL11.GL_POLYGON_OFFSET_POINT");
        registerConstant(10754, "GL11.GL_POLYGON_OFFSET_LINE");
        registerConstant(32823, "GL11.GL_POLYGON_OFFSET_FILL");
        registerConstant(32827, "GL11.GL_ALPHA4");
        registerConstant(32828, "GL11.GL_ALPHA8");
        registerConstant(32829, "GL11.GL_ALPHA12");
        registerConstant(32830, "GL11.GL_ALPHA16");
        registerConstant(32831, "GL11.GL_LUMINANCE4");
        registerConstant(32832, "GL11.GL_LUMINANCE8");
        registerConstant(32833, "GL11.GL_LUMINANCE12");
        registerConstant(32834, "GL11.GL_LUMINANCE16");
        registerConstant(32835, "GL11.GL_LUMINANCE4_ALPHA4");
        registerConstant(32836, "GL11.GL_LUMINANCE6_ALPHA2");
        registerConstant(32837, "GL11.GL_LUMINANCE8_ALPHA8");
        registerConstant(32838, "GL11.GL_LUMINANCE12_ALPHA4");
        registerConstant(32839, "GL11.GL_LUMINANCE12_ALPHA12");
        registerConstant(32840, "GL11.GL_LUMINANCE16_ALPHA16");
        registerConstant(32841, "GL11.GL_INTENSITY");
        registerConstant(32842, "GL11.GL_INTENSITY4");
        registerConstant(32843, "GL11.GL_INTENSITY8");
        registerConstant(32844, "GL11.GL_INTENSITY12");
        registerConstant(32845, "GL11.GL_INTENSITY16");
        registerConstant(10768, "GL11.GL_R3_G3_B2");
        registerConstant(32847, "GL11.GL_RGB4");
        registerConstant(32848, "GL11.GL_RGB5");
        registerConstant(32849, "GL11.GL_RGB8");
        registerConstant(32850, "GL11.GL_RGB10");
        registerConstant(32851, "GL11.GL_RGB12");
        registerConstant(32852, "GL11.GL_RGB16");
        registerConstant(32853, "GL11.GL_RGBA2");
        registerConstant(32854, "GL11.GL_RGBA4");
        registerConstant(32855, "GL11.GL_RGB5_A1");
        registerConstant(32856, "GL11.GL_RGBA8");
        registerConstant(32857, "GL11.GL_RGB10_A2");
        registerConstant(32858, "GL11.GL_RGBA12");
        registerConstant(32859, "GL11.GL_RGBA16");
        registerConstant(32860, "GL11.GL_TEXTURE_RED_SIZE");
        registerConstant(32861, "GL11.GL_TEXTURE_GREEN_SIZE");
        registerConstant(32862, "GL11.GL_TEXTURE_BLUE_SIZE");
        registerConstant(32863, "GL11.GL_TEXTURE_ALPHA_SIZE");
        registerConstant(32864, "GL11.GL_TEXTURE_LUMINANCE_SIZE");
        registerConstant(32865, "GL11.GL_TEXTURE_INTENSITY_SIZE");
        registerConstant(32867, "GL11.GL_PROXY_TEXTURE_1D");
        registerConstant(32868, "GL11.GL_PROXY_TEXTURE_2D");
        registerConstant(32870, "GL11.GL_TEXTURE_PRIORITY");
        registerConstant(32871, "GL11.GL_TEXTURE_RESIDENT");
        registerConstant(32872, "GL11.GL_TEXTURE_BINDING_1D");
        registerConstant(32873, "GL11.GL_TEXTURE_BINDING_2D");
        registerConstant(32884, "GL11.GL_VERTEX_ARRAY");
        registerConstant(32885, "GL11.GL_NORMAL_ARRAY");
        registerConstant(32886, "GL11.GL_COLOR_ARRAY");
        registerConstant(32887, "GL11.GL_INDEX_ARRAY");
        registerConstant(32888, "GL11.GL_TEXTURE_COORD_ARRAY");
        registerConstant(32889, "GL11.GL_EDGE_FLAG_ARRAY");
        registerConstant(32890, "GL11.GL_VERTEX_ARRAY_SIZE");
        registerConstant(32891, "GL11.GL_VERTEX_ARRAY_TYPE");
        registerConstant(32892, "GL11.GL_VERTEX_ARRAY_STRIDE");
        registerConstant(32894, "GL11.GL_NORMAL_ARRAY_TYPE");
        registerConstant(32895, "GL11.GL_NORMAL_ARRAY_STRIDE");
        registerConstant(32897, "GL11.GL_COLOR_ARRAY_SIZE");
        registerConstant(32898, "GL11.GL_COLOR_ARRAY_TYPE");
        registerConstant(32899, "GL11.GL_COLOR_ARRAY_STRIDE");
        registerConstant(32901, "GL11.GL_INDEX_ARRAY_TYPE");
        registerConstant(32902, "GL11.GL_INDEX_ARRAY_STRIDE");
        registerConstant(32904, "GL11.GL_TEXTURE_COORD_ARRAY_SIZE");
        registerConstant(32905, "GL11.GL_TEXTURE_COORD_ARRAY_TYPE");
        registerConstant(32906, "GL11.GL_TEXTURE_COORD_ARRAY_STRIDE");
        registerConstant(32908, "GL11.GL_EDGE_FLAG_ARRAY_STRIDE");
        registerConstant(32910, "GL11.GL_VERTEX_ARRAY_POINTER");
        registerConstant(32911, "GL11.GL_NORMAL_ARRAY_POINTER");
        registerConstant(32912, "GL11.GL_COLOR_ARRAY_POINTER");
        registerConstant(32913, "GL11.GL_INDEX_ARRAY_POINTER");
        registerConstant(32914, "GL11.GL_TEXTURE_COORD_ARRAY_POINTER");
        registerConstant(32915, "GL11.GL_EDGE_FLAG_ARRAY_POINTER");
        registerConstant(10784, "GL11.GL_V2F");
        registerConstant(10785, "GL11.GL_V3F");
        registerConstant(10786, "GL11.GL_C4UB_V2F");
        registerConstant(10787, "GL11.GL_C4UB_V3F");
        registerConstant(10788, "GL11.GL_C3F_V3F");
        registerConstant(10789, "GL11.GL_N3F_V3F");
        registerConstant(10790, "GL11.GL_C4F_N3F_V3F");
        registerConstant(10791, "GL11.GL_T2F_V3F");
        registerConstant(10792, "GL11.GL_T4F_V4F");
        registerConstant(10793, "GL11.GL_T2F_C4UB_V3F");
        registerConstant(10794, "GL11.GL_T2F_C3F_V3F");
        registerConstant(10795, "GL11.GL_T2F_N3F_V3F");
        registerConstant(10796, "GL11.GL_T2F_C4F_N3F_V3F");
        registerConstant(10797, "GL11.GL_T4F_C4F_N3F_V4F");
        registerConstant(3057, "GL11.GL_LOGIC_OP");
        registerConstant(4099, "GL11.GL_TEXTURE_COMPONENTS");
        registerConstant(32874, "GL12.GL_TEXTURE_BINDING_3D");
        registerConstant(32875, "GL12.GL_PACK_SKIP_IMAGES");
        registerConstant(32876, "GL12.GL_PACK_IMAGE_HEIGHT");
        registerConstant(32877, "GL12.GL_UNPACK_SKIP_IMAGES");
        registerConstant(32878, "GL12.GL_UNPACK_IMAGE_HEIGHT");
        registerConstant(32879, "GL12.GL_TEXTURE_3D");
        registerConstant(32880, "GL12.GL_PROXY_TEXTURE_3D");
        registerConstant(32881, "GL12.GL_TEXTURE_DEPTH");
        registerConstant(32882, "GL12.GL_TEXTURE_WRAP_R");
        registerConstant(32883, "GL12.GL_MAX_3D_TEXTURE_SIZE");
        registerConstant(32992, "GL12.GL_BGR");
        registerConstant(32993, "GL12.GL_BGRA");
        registerConstant(32818, "GL12.GL_UNSIGNED_BYTE_3_3_2");
        registerConstant(33634, "GL12.GL_UNSIGNED_BYTE_2_3_3_REV");
        registerConstant(33635, "GL12.GL_UNSIGNED_SHORT_5_6_5");
        registerConstant(33636, "GL12.GL_UNSIGNED_SHORT_5_6_5_REV");
        registerConstant(32819, "GL12.GL_UNSIGNED_SHORT_4_4_4_4");
        registerConstant(33637, "GL12.GL_UNSIGNED_SHORT_4_4_4_4_REV");
        registerConstant(32820, "GL12.GL_UNSIGNED_SHORT_5_5_5_1");
        registerConstant(33638, "GL12.GL_UNSIGNED_SHORT_1_5_5_5_REV");
        registerConstant(32821, "GL12.GL_UNSIGNED_INT_8_8_8_8");
        registerConstant(33639, "GL12.GL_UNSIGNED_INT_8_8_8_8_REV");
        registerConstant(32822, "GL12.GL_UNSIGNED_INT_10_10_10_2");
        registerConstant(33640, "GL12.GL_UNSIGNED_INT_2_10_10_10_REV");
        registerConstant(32826, "GL12.GL_RESCALE_NORMAL");
        registerConstant(33272, "GL12.GL_LIGHT_MODEL_COLOR_CONTROL");
        registerConstant(33273, "GL12.GL_SINGLE_COLOR");
        registerConstant(33274, "GL12.GL_SEPARATE_SPECULAR_COLOR");
        registerConstant(33071, "GL12.GL_CLAMP_TO_EDGE");
        registerConstant(33082, "GL12.GL_TEXTURE_MIN_LOD");
        registerConstant(33083, "GL12.GL_TEXTURE_MAX_LOD");
        registerConstant(33084, "GL12.GL_TEXTURE_BASE_LEVEL");
        registerConstant(33085, "GL12.GL_TEXTURE_MAX_LEVEL");
        registerConstant(33000, "GL12.GL_MAX_ELEMENTS_VERTICES");
        registerConstant(33001, "GL12.GL_MAX_ELEMENTS_INDICES");
        registerConstant(33901, "GL12.GL_ALIASED_POINT_SIZE_RANGE");
        registerConstant(33902, "GL12.GL_ALIASED_LINE_WIDTH_RANGE");
        registerConstant(33984, "GL13.GL_TEXTURE0");
        registerConstant(33985, "GL13.GL_TEXTURE1");
        registerConstant(33986, "GL13.GL_TEXTURE2");
        registerConstant(33987, "GL13.GL_TEXTURE3");
        registerConstant(33988, "GL13.GL_TEXTURE4");
        registerConstant(33989, "GL13.GL_TEXTURE5");
        registerConstant(33990, "GL13.GL_TEXTURE6");
        registerConstant(33991, "GL13.GL_TEXTURE7");
        registerConstant(33992, "GL13.GL_TEXTURE8");
        registerConstant(33993, "GL13.GL_TEXTURE9");
        registerConstant(33994, "GL13.GL_TEXTURE10");
        registerConstant(33995, "GL13.GL_TEXTURE11");
        registerConstant(33996, "GL13.GL_TEXTURE12");
        registerConstant(33997, "GL13.GL_TEXTURE13");
        registerConstant(33998, "GL13.GL_TEXTURE14");
        registerConstant(33999, "GL13.GL_TEXTURE15");
        registerConstant(34000, "GL13.GL_TEXTURE16");
        registerConstant(34001, "GL13.GL_TEXTURE17");
        registerConstant(34002, "GL13.GL_TEXTURE18");
        registerConstant(34003, "GL13.GL_TEXTURE19");
        registerConstant(34004, "GL13.GL_TEXTURE20");
        registerConstant(34005, "GL13.GL_TEXTURE21");
        registerConstant(34006, "GL13.GL_TEXTURE22");
        registerConstant(34007, "GL13.GL_TEXTURE23");
        registerConstant(34008, "GL13.GL_TEXTURE24");
        registerConstant(34009, "GL13.GL_TEXTURE25");
        registerConstant(34010, "GL13.GL_TEXTURE26");
        registerConstant(34011, "GL13.GL_TEXTURE27");
        registerConstant(34012, "GL13.GL_TEXTURE28");
        registerConstant(34013, "GL13.GL_TEXTURE29");
        registerConstant(34014, "GL13.GL_TEXTURE30");
        registerConstant(34015, "GL13.GL_TEXTURE31");
        registerConstant(34016, "GL13.GL_ACTIVE_TEXTURE");
        registerConstant(34017, "GL13.GL_CLIENT_ACTIVE_TEXTURE");
        registerConstant(34018, "GL13.GL_MAX_TEXTURE_UNITS");
        registerConstant(34065, "GL13.GL_NORMAL_MAP");
        registerConstant(34066, "GL13.GL_REFLECTION_MAP");
        registerConstant(34067, "GL13.GL_TEXTURE_CUBE_MAP");
        registerConstant(34068, "GL13.GL_TEXTURE_BINDING_CUBE_MAP");
        registerConstant(34069, "GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X");
        registerConstant(34070, "GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_X");
        registerConstant(34071, "GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Y");
        registerConstant(34072, "GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y");
        registerConstant(34073, "GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Z");
        registerConstant(34074, "GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z");
        registerConstant(34075, "GL13.GL_PROXY_TEXTURE_CUBE_MAP");
        registerConstant(34076, "GL13.GL_MAX_CUBE_MAP_TEXTURE_SIZE");
        registerConstant(34025, "GL13.GL_COMPRESSED_ALPHA");
        registerConstant(34026, "GL13.GL_COMPRESSED_LUMINANCE");
        registerConstant(34027, "GL13.GL_COMPRESSED_LUMINANCE_ALPHA");
        registerConstant(34028, "GL13.GL_COMPRESSED_INTENSITY");
        registerConstant(34029, "GL13.GL_COMPRESSED_RGB");
        registerConstant(34030, "GL13.GL_COMPRESSED_RGBA");
        registerConstant(34031, "GL13.GL_TEXTURE_COMPRESSION_HINT");
        registerConstant(34464, "GL13.GL_TEXTURE_COMPRESSED_IMAGE_SIZE");
        registerConstant(34465, "GL13.GL_TEXTURE_COMPRESSED");
        registerConstant(34466, "GL13.GL_NUM_COMPRESSED_TEXTURE_FORMATS");
        registerConstant(34467, "GL13.GL_COMPRESSED_TEXTURE_FORMATS");
        registerConstant(32925, "GL13.GL_MULTISAMPLE");
        registerConstant(32926, "GL13.GL_SAMPLE_ALPHA_TO_COVERAGE");
        registerConstant(32927, "GL13.GL_SAMPLE_ALPHA_TO_ONE");
        registerConstant(32928, "GL13.GL_SAMPLE_COVERAGE");
        registerConstant(32936, "GL13.GL_SAMPLE_BUFFERS");
        registerConstant(32937, "GL13.GL_SAMPLES");
        registerConstant(32938, "GL13.GL_SAMPLE_COVERAGE_VALUE");
        registerConstant(32939, "GL13.GL_SAMPLE_COVERAGE_INVERT");
        registerConstant(34019, "GL13.GL_TRANSPOSE_MODELVIEW_MATRIX");
        registerConstant(34020, "GL13.GL_TRANSPOSE_PROJECTION_MATRIX");
        registerConstant(34021, "GL13.GL_TRANSPOSE_TEXTURE_MATRIX");
        registerConstant(34022, "GL13.GL_TRANSPOSE_COLOR_MATRIX");
        registerConstant(34160, "GL13.GL_COMBINE");
        registerConstant(34161, "GL13.GL_COMBINE_RGB");
        registerConstant(34162, "GL13.GL_COMBINE_ALPHA");
        registerConstant(34176, "GL13.GL_SOURCE0_RGB");
        registerConstant(34177, "GL13.GL_SOURCE1_RGB");
        registerConstant(34178, "GL13.GL_SOURCE2_RGB");
        registerConstant(34184, "GL13.GL_SOURCE0_ALPHA");
        registerConstant(34185, "GL13.GL_SOURCE1_ALPHA");
        registerConstant(34186, "GL13.GL_SOURCE2_ALPHA");
        registerConstant(34192, "GL13.GL_OPERAND0_RGB");
        registerConstant(34193, "GL13.GL_OPERAND1_RGB");
        registerConstant(34194, "GL13.GL_OPERAND2_RGB");
        registerConstant(34200, "GL13.GL_OPERAND0_ALPHA");
        registerConstant(34201, "GL13.GL_OPERAND1_ALPHA");
        registerConstant(34202, "GL13.GL_OPERAND2_ALPHA");
        registerConstant(34163, "GL13.GL_RGB_SCALE");
        registerConstant(34164, "GL13.GL_ADD_SIGNED");
        registerConstant(34165, "GL13.GL_INTERPOLATE");
        registerConstant(34023, "GL13.GL_SUBTRACT");
        registerConstant(34166, "GL13.GL_CONSTANT");
        registerConstant(34167, "GL13.GL_PRIMARY_COLOR");
        registerConstant(34168, "GL13.GL_PREVIOUS");
        registerConstant(34478, "GL13.GL_DOT3_RGB");
        registerConstant(34479, "GL13.GL_DOT3_RGBA");
        registerConstant(33069, "GL13.GL_CLAMP_TO_BORDER");
        registerConstant(33169, "GL14.GL_GENERATE_MIPMAP");
        registerConstant(33170, "GL14.GL_GENERATE_MIPMAP_HINT");
        registerConstant(33189, "GL14.GL_DEPTH_COMPONENT16");
        registerConstant(33190, "GL14.GL_DEPTH_COMPONENT24");
        registerConstant(33191, "GL14.GL_DEPTH_COMPONENT32");
        registerConstant(34890, "GL14.GL_TEXTURE_DEPTH_SIZE");
        registerConstant(34891, "GL14.GL_DEPTH_TEXTURE_MODE");
        registerConstant(34892, "GL14.GL_TEXTURE_COMPARE_MODE");
        registerConstant(34893, "GL14.GL_TEXTURE_COMPARE_FUNC");
        registerConstant(34894, "GL14.GL_COMPARE_R_TO_TEXTURE");
        registerConstant(33872, "GL14.GL_FOG_COORDINATE_SOURCE");
        registerConstant(33873, "GL14.GL_FOG_COORDINATE");
        registerConstant(33874, "GL14.GL_FRAGMENT_DEPTH");
        registerConstant(33875, "GL14.GL_CURRENT_FOG_COORDINATE");
        registerConstant(33876, "GL14.GL_FOG_COORDINATE_ARRAY_TYPE");
        registerConstant(33877, "GL14.GL_FOG_COORDINATE_ARRAY_STRIDE");
        registerConstant(33878, "GL14.GL_FOG_COORDINATE_ARRAY_POINTER");
        registerConstant(33879, "GL14.GL_FOG_COORDINATE_ARRAY");
        registerConstant(33062, "GL14.GL_POINT_SIZE_MIN");
        registerConstant(33063, "GL14.GL_POINT_SIZE_MAX");
        registerConstant(33064, "GL14.GL_POINT_FADE_THRESHOLD_SIZE");
        registerConstant(33065, "GL14.GL_POINT_DISTANCE_ATTENUATION");
        registerConstant(33880, "GL14.GL_COLOR_SUM");
        registerConstant(33881, "GL14.GL_CURRENT_SECONDARY_COLOR");
        registerConstant(33882, "GL14.GL_SECONDARY_COLOR_ARRAY_SIZE");
        registerConstant(33883, "GL14.GL_SECONDARY_COLOR_ARRAY_TYPE");
        registerConstant(33884, "GL14.GL_SECONDARY_COLOR_ARRAY_STRIDE");
        registerConstant(33885, "GL14.GL_SECONDARY_COLOR_ARRAY_POINTER");
        registerConstant(33886, "GL14.GL_SECONDARY_COLOR_ARRAY");
        registerConstant(32968, "GL14.GL_BLEND_DST_RGB");
        registerConstant(32969, "GL14.GL_BLEND_SRC_RGB");
        registerConstant(32970, "GL14.GL_BLEND_DST_ALPHA");
        registerConstant(32971, "GL14.GL_BLEND_SRC_ALPHA");
        registerConstant(34055, "GL14.GL_INCR_WRAP");
        registerConstant(34056, "GL14.GL_DECR_WRAP");
        registerConstant(34048, "GL14.GL_TEXTURE_FILTER_CONTROL");
        registerConstant(34049, "GL14.GL_TEXTURE_LOD_BIAS");
        registerConstant(34045, "GL14.GL_MAX_TEXTURE_LOD_BIAS");
        registerConstant(33648, "GL14.GL_MIRRORED_REPEAT");
        registerConstant(32773, "ARBImaging.GL_BLEND_COLOR");
        registerConstant(32777, "ARBImaging.GL_BLEND_EQUATION");
        registerConstant(32774, "GL14.GL_FUNC_ADD");
        registerConstant(32778, "GL14.GL_FUNC_SUBTRACT");
        registerConstant(32779, "GL14.GL_FUNC_REVERSE_SUBTRACT");
        registerConstant(32775, "GL14.GL_MIN");
        registerConstant(32776, "GL14.GL_MAX");
        registerConstant(34962, "GL15.GL_ARRAY_BUFFER");
        registerConstant(34963, "GL15.GL_ELEMENT_ARRAY_BUFFER");
        registerConstant(34964, "GL15.GL_ARRAY_BUFFER_BINDING");
        registerConstant(34965, "GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING");
        registerConstant(34966, "GL15.GL_VERTEX_ARRAY_BUFFER_BINDING");
        registerConstant(34967, "GL15.GL_NORMAL_ARRAY_BUFFER_BINDING");
        registerConstant(34968, "GL15.GL_COLOR_ARRAY_BUFFER_BINDING");
        registerConstant(34969, "GL15.GL_INDEX_ARRAY_BUFFER_BINDING");
        registerConstant(34970, "GL15.GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING");
        registerConstant(34971, "GL15.GL_EDGE_FLAG_ARRAY_BUFFER_BINDING");
        registerConstant(34972, "GL15.GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING");
        registerConstant(34973, "GL15.GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING");
        registerConstant(34974, "GL15.GL_WEIGHT_ARRAY_BUFFER_BINDING");
        registerConstant(34975, "GL15.GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING");
        registerConstant(35040, "GL15.GL_STREAM_DRAW");
        registerConstant(35041, "GL15.GL_STREAM_READ");
        registerConstant(35042, "GL15.GL_STREAM_COPY");
        registerConstant(35044, "GL15.GL_STATIC_DRAW");
        registerConstant(35045, "GL15.GL_STATIC_READ");
        registerConstant(35046, "GL15.GL_STATIC_COPY");
        registerConstant(35048, "GL15.GL_DYNAMIC_DRAW");
        registerConstant(35049, "GL15.GL_DYNAMIC_READ");
        registerConstant(35050, "GL15.GL_DYNAMIC_COPY");
        registerConstant(35000, "GL15.GL_READ_ONLY");
        registerConstant(35001, "GL15.GL_WRITE_ONLY");
        registerConstant(35002, "GL15.GL_READ_WRITE");
        registerConstant(34660, "GL15.GL_BUFFER_SIZE");
        registerConstant(34661, "GL15.GL_BUFFER_USAGE");
        registerConstant(35003, "GL15.GL_BUFFER_ACCESS");
        registerConstant(35004, "GL15.GL_BUFFER_MAPPED");
        registerConstant(35005, "GL15.GL_BUFFER_MAP_POINTER");
        registerConstant(34138, "NVFogDistance.GL_FOG_DISTANCE_MODE_NV");
        registerConstant(34139, "NVFogDistance.GL_EYE_RADIAL_NV");
        registerConstant(34140, "NVFogDistance.GL_EYE_PLANE_ABSOLUTE_NV");
        j = Maps.newHashMap();
    }
}
