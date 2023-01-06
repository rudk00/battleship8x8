package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        long bitmask = 0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        switch (shot.charAt(0)) {
            case 'A': break;
            case 'B': bitmask >>>= 1; break;
            case 'C': bitmask >>>= 2; break;
            case 'D': bitmask >>>= 3; break;
            case 'E': bitmask >>>= 4; break;
            case 'F': bitmask >>>= 5; break;
            case 'G': bitmask >>>= 6; break;
            case 'H': bitmask >>>= 7; break;
            default: throw new IllegalArgumentException();
        }
        bitmask >>>= (8 * (shot.charAt(1) - 1));
        shots |= bitmask;
        return ships == (ships | bitmask);
    }
    public String state() {
        String shipsString = Long.toBinaryString(ships);
        String shotsString = Long.toBinaryString(shots);
        StringBuilder zero = new StringBuilder();
        if (shipsString.length() < 64) {
            zero.append("0".repeat(64 - shipsString.length()));
            shipsString = zero.append(shipsString).toString();
            zero.delete(0, zero.length());
        }
        if (shotsString.length() < 64) {
            zero.append("0".repeat(64 - shotsString.length()));
            shotsString = zero.append(shotsString).toString();
            zero.delete(0, zero.length());
        }
        StringBuilder res = new StringBuilder();
        //'.' - an empty cell
        //'×' - an empty cell that has been shot
        //'☐' - a cell seized by a ship
        //'☒'- a cell seized by a ship that has been shot
        for (int i = 0; i < shotsString.length(); i++) {
            if (i % 8 == 0) {
                res.append('\n');
            }
            if (shotsString.charAt(i) == '0' && shipsString.charAt(i) == '0') {
                res.append('.');
            } else if (shotsString.charAt(i) == '1' && shipsString.charAt(i) == '0') {
                res.append('×');
            } else if (shotsString.charAt(i) == '0' && shipsString.charAt(i) == '1') {
                res.append('☐');
            } else if (shotsString.charAt(i) == '1' && shipsString.charAt(i) == '1') {
                res.append('☒');
            }
        }
        return res.toString();
    }
}