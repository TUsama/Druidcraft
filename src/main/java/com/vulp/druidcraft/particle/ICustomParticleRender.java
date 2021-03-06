package com.vulp.druidcraft.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public interface ICustomParticleRender extends IParticleRenderType {

    ICustomParticleRender PARTICLE_SHEET_TRANSLUCENT_GLOW = new ICustomParticleRender() {
        @Override
        public void beginRender(BufferBuilder buffer, TextureManager textureManager) {
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableAlphaTest();
            GlStateManager.enableBlend();
            GlStateManager.depthMask(false);
            textureManager.bindTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE.param);
            GlStateManager.alphaFunc(516, 0.003921569F);
            GlStateManager.disableCull();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        }
        @Override
        public void finishRender(Tessellator tess) {
            tess.draw();
        }
        public String toString() {
            return "PARTICLE_SHEET_TRANSLUCENT_GLOW";
        }
    };

    ICustomParticleRender CUSTOM = new ICustomParticleRender() {
        @Override
        public void beginRender(BufferBuilder p_217600_1_, TextureManager p_217600_2_) {
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            p_217600_2_.bindTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE);
            p_217600_1_.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        }

        @Override
        public void finishRender(Tessellator p_217599_1_) {
            p_217599_1_.draw();
        }

        public String toString() {
            return "CUSTOM";
        }
    };
}
