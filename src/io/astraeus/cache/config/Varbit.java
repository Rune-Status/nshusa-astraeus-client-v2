package io.astraeus.cache.config;

import io.astraeus.cache.FileArchive;
import io.astraeus.io.Buffer;

public final class Varbit {

	public static Varbit varbits[];
	public int setting = -1;
	public int low = -1;
	public int high = -1;
	private boolean aBoolean651;

	public static void init(FileArchive streamLoader) {
		Buffer datBuf = new Buffer(streamLoader.readFile("varbit.dat"));

		final int size = datBuf.readUShort();

		if (varbits == null) {
			varbits = new Varbit[size];
		}

		for (int index = 0; index < size; index++) {

			if (varbits[index] == null) {
				varbits[index] = new Varbit();
			}

			varbits[index].decode(datBuf);

			if (varbits[index].aBoolean651) {
				Varp.variables[varbits[index].setting].aBoolean713 = true;
			}

		}

		if (datBuf.currentPosition != datBuf.payload.length) {
			System.out.println("varbit load mismatch");
		}

	}

	private void decode(Buffer stream) {
		setting = stream.readUShort();
		low = stream.readUnsignedByte();
		high = stream.readUnsignedByte();

	}

	private Varbit() {
		aBoolean651 = false;
	}

	public int getSetting() {
		return setting;
	}

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}

}
