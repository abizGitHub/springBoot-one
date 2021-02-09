package abiz.ir.crwlr.some;

import lombok.*;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class T2 {


    public static void main(String[] args) {
        Dto d1 = Dto.builder().i(1).s("s1").build();
        Dto d2 = Dto.builder().i(2).s("s2").neis(Arrays.asList(d1)).build();
        Dto d3 = Dto.builder().i(3).s("s3").neis(Arrays.asList(d1)).neis(Arrays.asList(d2)).build();
        Dto d4 = Dto.builder().i(4).s("s4").neis(Arrays.asList(d1)).neis(Arrays.asList(d2)).neis(Arrays.asList(d3)).build();
        Map<Integer, String> c1 = Arrays.asList(d1, d2, d3, d4).stream().collect(new Mcollector());

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class Dto {
        int i;
        String s;
        List<Dto> neis;
    }

    static class Mcollector implements Collector<Dto, List<Dto>, Map<Integer, String>> {


        @Override
        public Supplier<List<Dto>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<Dto>, Dto> accumulator() {
            return (list, dto) -> list.add(dto);
        }

        @Override
        public BinaryOperator<List<Dto>> combiner() {
            return (list1, list2) -> {
                list2.addAll(list1);
                return list2;
            };
        }

        @Override
        public Function<List<Dto>, Map<Integer, String>> finisher() {
            return (list -> {
                Map<Integer, StringBuffer> map = new HashMap<>();
                list.stream().forEach(
                        dto -> {
                            StringBuffer s = map.get(dto.getI());
                            map.put(dto.getI(),s.append(dto.s));
                        }
                );

                return null;
            });
        }

        @Override
        public Set<Characteristics> characteristics() {
            return null;
        }
    }

}
