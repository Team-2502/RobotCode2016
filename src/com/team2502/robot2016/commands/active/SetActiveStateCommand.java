package com.team2502.robot2016.commands.active;

import com.team2502.robot2016.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class SetActiveStateCommand extends Command {
    
    private boolean down;
    
    public SetActiveStateCommand(boolean down)
    {
        this.down = down;
        
        requires(Robot.activeFrame);
    }
    
    public void initialize()
    {
        Robot.activeFrame.setActivePickupState(down);
    }
    
    public void execute()
    {
        
    }
    
    public boolean isFinished()
    {
        return true;
    }
    
    public void end()
    {
        
    }

    public void interrupted() {
        
    }
    
}
