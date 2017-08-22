package com.sloverse.extension.zone.simulation.room.bounds;

import java.util.ArrayList;
import java.util.List;

import com.sloverse.extension.zone.simulation.player.Player;
import com.sloverse.extension.zone.simulation.room.RoomCoordinate;

public class RoomBounds 
{
	private List<RoomCoordinate> roomBoundCoordinates;
	//private List<RoomBoundLine> roomBoundLines;
	
	public RoomBounds(ArrayList<RoomCoordinate> roomBounds)
	{
		roomBoundCoordinates = roomBounds;
	}
	
	public boolean isPlayerInRoomBounds(Player player) 
	{
		RoomCoordinate playerPosition = player.getPositionInRoom().getRoomPosition();
		
		if (!isPlayerInAABoundingBox(playerPosition)) return false;
		
		//For now we are just testing the basic bounding box.
		//In the future, test if inside polygon.
		
		return true;
	}
	
	private boolean isPlayerInAABoundingBox(RoomCoordinate playerPosition)
	{
		float minX = RoomCoordinate.MAX_BOUND, minY = RoomCoordinate.MAX_BOUND;
		float maxX = RoomCoordinate.MIN_BOUND, maxY = RoomCoordinate.MIN_BOUND;
		
		for (int i = 0; i < roomBoundCoordinates.size(); ++i)
		{
			float xCoord = roomBoundCoordinates.get(i).x;
			float yCoord = roomBoundCoordinates.get(i).y;
			
			if (xCoord < minX) minX = xCoord;
			if (yCoord < minY) minY = yCoord;
			if (xCoord > maxX) maxX = xCoord;
			if (yCoord > maxY) maxY = yCoord;
		}
		
		if (playerPosition.x < minX || playerPosition.x > maxX ||
			playerPosition.y < minY || playerPosition.y > maxY)
		{
			return false;
		}
		
		return true;
	}

	public RoomCoordinate clampTargetToRoomBounds(RoomCoordinate requestedTargetPosition) 
	{
		//IN THE FUTURE. 
		//LOOK AT 3D MATH TEXT BOOK PG 96 AND FIND CLOSEST INTERSECTION BETWEEN PLAYER POINT, ORIGINAL TARGET POINT AND BOUNDS.
		
		return requestedTargetPosition;
	}
}
