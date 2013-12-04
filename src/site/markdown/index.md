This transformation API can be implemented to simplify the transformation of tokens within text. All that is required is
the implementation of a [`Transformer`](apidocs/shiver/me/timbers/Transformer.html) along with some
[`Transformations`](apidocs/shiver/me/timbers/Transformations.html).

For example a simple parsing transformer could be implemented as follows.

```
Transformer transformer = new Transformer() {

    @Override
    public String transform(InputStream stream, Transformations transformations) {

        String[] strings = read(stream).split(" ");

        StringBuilder builder = new StringBuilder();

        for (String string : strings) {

            builder.append(transformations.get(string)).append(" ");
        }

        return builder.toString();
    }
};
```

This will apply any supplied transformations to all the words in any supplied text. So, if we wanted to replace the
numbers 1-5 with their equivalent words we could implement the following
[`Transformation`](apidocs/shiver/me/timbers/Transformation.html)s.

```
Transformation t1 = new Transformation() {

    @Override
    public String getName() {

        return "1";
    }

    @Override
    public String apply(String string) {

        return "one";
    }
};

Transformation t2 = new Transformation() {/*...*/};

Transformation t3 = new Transformation() {/*...*/};

Transformation t4 = new Transformation() {/*...*/};

Transformation t5 = new Transformation() {/*...*/};
```

Then run them against some text.

```
String text = "Some text that has 1 or 2 numbers in it... 3 4 5 6";

InputStream stream = new ByteArrayInputStream(text.getBytes(Charset.forName("UTF-8")));

transformer.transform(stream, new IndividualTransformations(asList(t1, t2, t3, t4, t5)));
// Some text that has one or two numbers in it... three four five 6
```