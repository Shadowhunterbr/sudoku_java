package main.model;

import java.util.Collection;
import java.util.List;

import main.model.Space;

import static main.model.GameStatusEnum.NON_STARTED;
import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

public class Board {
	
	 
	
	private final List<List<Space>> spaces;
	
	public Board (final List<List<Space>> spaces) {
		this.spaces = spaces;
		
	}

	public List<List<Space>> getSpaces() {
		return spaces;
	}
	
	
	public GameStatusEnum getStatusEnum() {
		
		if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))){
			return NON_STARTED;

		}
		
		return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? GameStatusEnum.INCOMPLETE : GameStatusEnum.COMPLETE;
	}
	
	public boolean hasErrors() {
		if(getStatusEnum() == NON_STARTED) {
			return false;
		}
		return spaces.stream().flatMap(Collection::stream).anyMatch(s -> nonNull(s.getActual()) && s.getActual().equals(s.getExpected()));
		
	}
	
	public boolean changeValue(final int col, final int row, final Integer value) {
		var space = spaces.get(col).get(row);
		if (space.isFixed()) {
			return false;
		}
		
		space.setActual(value);
		return true;
		
	
	}
	
	public boolean clearValue(final int col, final int row) {
		var space  = spaces.get(col).get(row);
		if (space.isFixed()) {
			return false;
		}
		space.clearSpace();
		return true;
	}
	
	public void reset() {
		spaces.forEach(c -> c.forEach(Space::clearSpace));
	}
	
	public boolean gameIsFinished() {
		return !hasErrors() && getStatusEnum() == GameStatusEnum.COMPLETE; // .equals
		
	}
}

