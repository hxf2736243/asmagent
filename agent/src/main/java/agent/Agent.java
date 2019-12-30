package agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * dddd ddd
 * @author githhub
 * @date 2019-12-30
 */
public class Agent {
	/**
	 *
	 * @param agentArgs
	 * @param inst
	 */
	public static void premain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new ClassFileTransformer() {
			public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) {

				if ("other/Stuff".equals(s)) {
					// ASM Code
					ClassReader reader = new ClassReader(bytes);
					ClassWriter writer = new ClassWriter(reader, 0);
					ClassPrinter visitor = new ClassPrinter(writer);
					reader.accept(visitor, 0);
					return writer.toByteArray();

				}

				return null;
			}
		});
	}
}
