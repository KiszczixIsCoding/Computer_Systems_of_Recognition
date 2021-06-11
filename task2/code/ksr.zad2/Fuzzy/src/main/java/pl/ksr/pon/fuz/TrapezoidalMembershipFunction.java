package pl.ksr.pon.fuz;

import io.vavr.Tuple2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class TrapezoidalMembershipFunction extends MembershipFunction {
    double x1, x2, x3;
}
