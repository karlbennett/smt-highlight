This is the API that is implemented by all the SMT highlight libraries. It doesn't contain any implementation its self.

There is one class though, the [`WrappedHighlighter`](apidocs/shiver/me/timbers/WrappedHighlighter.html), that can be used to store a highlighter and any
[`Highlight`s](apidocs/shiver/me/timbers/Highlight.html) together.

#### Highlight

The `Highlight` interface just exposes two methods `Highlight#getName():String` and `Highlight#apply(String):String` which
expose the name of the highlight and apply it to a string.

The name is mainly used to define the type of token the highlight should be applied to.

#### Highlighter

The [`Highlighter`](apidocs/shiver/me/timbers/Highlighter.html) interface exposes a single `highlight(InputStream,Map<String, Highlight>):String` method that
should be implemented with the logic that will apply the highlights supplied in the map to the text supplied in the
input stream.