package dk.shadowerlort.minthygge.utils;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Direction {
    private final double pitch;
    private final double yaw;
    private final double length;

    public Direction(double pitch, double yaw, double length) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.length = length;
    }

    public Vector getDirection(Entity entity) {
        Location entityLocation = entity.getLocation();
        double pitchRadians = Math.toRadians(entityLocation.getPitch() + pitch);
        double yawRadians = Math.toRadians(entityLocation.getYaw() + yaw);

        double x = Math.cos(yawRadians) * Math.cos(pitchRadians) * length;
        double y = Math.sin(pitchRadians) * length;
        double z = Math.sin(yawRadians) * Math.cos(pitchRadians) * length;

        return new Vector(x, y, z);
    }

    public Vector getDirection(Location location) {
        double pitchRadians = Math.toRadians(location.getPitch() + pitch);
        double yawRadians = Math.toRadians(location.getYaw() + yaw);

        double x = Math.cos(yawRadians) * Math.cos(pitchRadians) * length;
        double y = Math.sin(pitchRadians) * length;
        double z = Math.sin(yawRadians) * Math.cos(pitchRadians) * length;

        return new Vector(x, y, z);
    }

    public Vector getDirection(BlockFace blockFace) {
        double pitchRadians = Math.toRadians(getPitchFromBlockFace(blockFace));
        double yawRadians = Math.toRadians(getYawFromBlockFace(blockFace));

        double x = Math.cos(yawRadians) * Math.cos(pitchRadians) * length;
        double y = Math.sin(pitchRadians) * length;
        double z = Math.sin(yawRadians) * Math.cos(pitchRadians) * length;

        return new Vector(x, y, z);
    }

    private float getPitchFromBlockFace(BlockFace blockFace) {
        switch (blockFace) {
            case UP:
                return -90;
            case DOWN:
                return 90;
            default:
                return 0;
        }
    }

    private float getYawFromBlockFace(BlockFace blockFace) {
        switch (blockFace) {
            case EAST:
                return 0;
            case SOUTH:
                return 90;
            case WEST:
                return 180;
            case NORTH:
                return -90;
            default:
                return 0;
        }
    }
}
