难受啊，@AliasFor这个注解只对Target(ElementType.Type,ElementType,method)有效，放在字段上的别名是不生效的

只有注解标记为@Retention(RetentionPolicy.RUNTIME)才可以在运行时通过Field.getDeclaredAnnotations()等方法获取到
CLASS和SOURCE的驻留策略都是不行的，主要看
private List<Field> getAllField() {
        Map<Integer, Field> fieldMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Field[] declaredFields = TestData.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(Arrays.toString(declaredField.getDeclaredAnnotations()));
            if (declaredField.isAnnotationPresent(XSSF.class)) {
                XSSF xssf = declaredField.getDeclaredAnnotation(XSSF.class);
                fieldMap.put(xssf.index(), declaredField);
            }
        }
        return new ArrayList<>(fieldMap.values());
    }

