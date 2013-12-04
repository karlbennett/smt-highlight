smt-transform
===========

This is the API that is implemented by all the SMT transformation libraries. It doesn't contain any stream processing
logic its self.

How ever it does contain some helper classes.

#### ApplyableTransformation

This is a very simple abstract implementation of the [`Transformation`]() interface that allows the [`Applyer`]() half
of the `Transformation` to be set as a constructor dependency.

#### CompoundTransformations

A [`Transformations`]() implementation that is used to relate a collection of `Transformation` names to a single
`Applyer` instance.

```java
Transformations transformations = new CompoundTransformations(asList("one", "two", "three"), new Applyer() {

    @Override
    public String apply(String string) {

        return "applied";
    }
});

transformations.get("one").apply("one"); // "applied"
transformations.get("two").apply("two"); // "applied"
transformations.get("three").apply("three"); // "applied"
transformations.get("four").apply("four"); // "four"
```

#### IndividualTransformations

This implementation of the `Transformations` interface can be populated with any `Iterable` object. This means it can be
populated from another `Transformations` instance.

```java
new IndividualTransformations(
    new IndividualTransformations(
        asList(transformationOne, transformationTwo, transformationThree)
    )
);
```
#### NullTransformation

This is just a `null` implementation of the `Transformation` interface that has the empty string as it's name and an
`apply(String)` that makes no change to the string passed to it.

#### WrappedTransformer

This `Transformer` implementation wraps a `Transformer` and `Transformations` together to simplify reapplying the two
over different texts. It also contains some helper methods for applying the `Transformations` to `String`s.
