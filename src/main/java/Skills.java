public class Skills {
    public int attacking_work_rate;
    public int defensive_work_rate;
    public int crossing;
    public int finishing;
    public int heading_accuracy;
    public int short_passing;
    public int volleys;
    public int dribbling;
    public int curve;
    public int free_kick_accuracy;
    public int long_passing;
    public int ball_control;
    public int acceleration;
    public int sprint_speed;
    public int agility;
    public int reactions;
    public int balance;
    public int shot_power;
    public int jumping;
    public int stamina;
    public int strength;
    public int long_shots;
    public int aggression;
    public int interceptions;
    public int positioning;
    public int vision;
    public int penalties;
    public int marking;
    public int standing_tackle;
    public int sliding_tackle;
    public int gk_diving;
    public int gk_handling;
    public int gk_kicking;
    public int gk_positioning;
    public int gk_reflexes;

    // Doesn't have a default (no-argument) constructor, since all players must have all these skills

    public Skills(int attacking_work_rate, int defensive_work_rate, int crossing, int finishing, int heading_accuracy,
                  int short_passing, int volleys, int dribbling, int curve, int free_kick_accuracy, int long_passing,
                  int ball_control, int acceleration, int sprint_speed, int agility, int reactions, int balance,
                  int shot_power, int jumping, int stamina, int strength, int long_shots, int aggression,
                  int interceptions, int positioning, int vision, int penalties, int marking, int standing_tackle,
                  int sliding_tackle, int gk_diving, int gk_handling, int gk_kicking, int gk_positioning,
                  int gk_reflexes) {
        this.attacking_work_rate = attacking_work_rate;
        this.defensive_work_rate = defensive_work_rate;
        this.crossing = crossing;
        this.finishing = finishing;
        this.heading_accuracy = heading_accuracy;
        this.short_passing = short_passing;
        this.volleys = volleys;
        this.dribbling = dribbling;
        this.curve = curve;
        this.free_kick_accuracy = free_kick_accuracy;
        this.long_passing = long_passing;
        this.ball_control = ball_control;
        this.acceleration = acceleration;
        this.sprint_speed = sprint_speed;
        this.agility = agility;
        this.reactions = reactions;
        this.balance = balance;
        this.shot_power = shot_power;
        this.jumping = jumping;
        this.stamina = stamina;
        this.strength = strength;
        this.long_shots = long_shots;
        this.aggression = aggression;
        this.interceptions = interceptions;
        this.positioning = positioning;
        this.vision = vision;
        this.penalties = penalties;
        this.marking = marking;
        this.standing_tackle = standing_tackle;
        this.sliding_tackle = sliding_tackle;
        this.gk_diving = gk_diving;
        this.gk_handling = gk_handling;
        this.gk_kicking = gk_kicking;
        this.gk_positioning = gk_positioning;
        this.gk_reflexes = gk_reflexes;
    }
}