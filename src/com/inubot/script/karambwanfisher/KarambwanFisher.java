package com.inubot.script.karambwanfisher;

import com.inubot.script.karambwanfisher.task.*;
import org.rspeer.commons.ArrayUtils;
import org.rspeer.commons.StopWatch;
import org.rspeer.event.Subscribe;
import org.rspeer.game.component.tdi.Skill;
import org.rspeer.game.event.ChatMessageEvent;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskScript;
import org.rspeer.game.script.meta.ScriptMeta;
import org.rspeer.game.script.meta.paint.PaintBinding;
import org.rspeer.game.script.meta.paint.PaintScheme;

import java.util.function.Supplier;

@ScriptMeta(
    name = "Karambwan Fisher",
    regions = -3,
    paint = PaintScheme.class,
    desc = "Fishes karambwans using the fairy ring method. Requires bait",
    developer = "Doga",
    version = 1.01
)
public class KarambwanFisher extends TaskScript {

  @PaintBinding("Runtime")
  private final StopWatch runtime = StopWatch.start();

  @PaintBinding("Last task")
  private final Supplier<String> task = () -> manager.getLastTaskName();

  @PaintBinding("Skill")
  private final Skill skills = Skill.FISHING;

  @PaintBinding(value = "Caught", rate = true)
  private int caught;

  @Override
  public Class<? extends Task>[] tasks() {
    return ArrayUtils.getTypeSafeArray(
        ToggleRunTask.class,
        StopTask.class,
        BankTask.class,
        FishTask.class
    );
  }

  @Subscribe
  public void notify(ChatMessageEvent event) {
    if (event.getContents().toLowerCase().contains("you catch a karambwan!")) {
      caught++;
    }
  }
}
