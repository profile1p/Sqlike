package fun.jiangjiang.sqlike.criteria.where;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class AndWhere extends AbstractWhere implements Where {

    private final List<Where> wheres;

    public AndWhere(Where... wheres) {
        this.wheres = Stream.ofNullable(wheres)
                .flatMap(Arrays::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Predicate<Object> render() {
        return o -> wheres.stream()
                .map(Where::render)
                .allMatch(predicate -> predicate.test(o));
    }
}
