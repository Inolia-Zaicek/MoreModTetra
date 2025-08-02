//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.inolia_zaicek.more_mod_tetra.Util;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import javax.annotation.ParametersAreNonnullByDefault;
import se.mickelus.mutil.util.HexCodec;

@ParametersAreNonnullByDefault
public class MMTCustomHexCodec implements PrimitiveCodec<Integer> {
    public static final HexCodec instance = new HexCodec();

    public <T> DataResult<Integer> read(DynamicOps<T> ops, T input) {
        return ops.getStringValue(input).map((val) -> (int)Long.parseLong(val, 16));
    }

    public <T> T write(DynamicOps<T> ops, Integer value) {
        return (T)ops.createString(Integer.toHexString(value));
    }

    public String toString() {
        return "mutil-hex";
    }
}
