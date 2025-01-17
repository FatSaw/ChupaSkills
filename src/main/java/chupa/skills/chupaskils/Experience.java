package chupa.skills.chupaskils;

import org.bukkit.entity.Player;

final class Experience {

	protected static int getExp(Player player) {
		return getExpFromLevel(player.getLevel()) + Math.round(getExpToNext(player.getLevel()) * player.getExp());
	}

	protected static int getExpFromLevel(int level) {
		if (level > 30) {
			return (int) (4.5 * level * level - 162.5 * level + 2220);
		}
		if (level > 15) {
			return (int) (2.5 * level * level - 40.5 * level + 360);
		}
		return level * level + 6 * level;
	}

	private static double getLevelFromExp(int exp) {
		int level = getIntLevelFromExp(exp);
		float remainder = exp - (float) getExpFromLevel(level);
		float progress = remainder / getExpToNext(level);
		return ((double) level) + progress;
	}

	private static int getIntLevelFromExp(int exp) {
		if (exp > 1395) {
			return (int) ((Math.sqrt(72 * exp - 54215D) + 325) / 18);
		}
		if (exp > 315) {
			return (int) (Math.sqrt(40 * exp - 7839D) / 10 + 8.1);
		}
		if (exp > 0) {
			return (int) (Math.sqrt(exp + 9D) - 3);
		}
		return 0;
	}

	private static int getExpToNext(int level) {
		if (level >= 30) {
			return level * 9 - 158;
		}
		if (level >= 15) {
			return level * 5 - 38;
		}
		return level * 2 + 7;
	}

	protected static void setExp(Player player, int exp) {
		double levelAndExp = getLevelFromExp(exp);
		int level = (int) levelAndExp;
		player.setLevel(level);
		player.setExp((float) (levelAndExp - level));
	}

	private Experience() {
	}

}