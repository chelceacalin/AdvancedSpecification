Implemented jpa specification with operations:

* Global : AND, OR
* Local: EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN

You can query it using POST using:
<pre>
{
    "globalOperator":"OR",
    "searchRequestDto": [
        {
            "column": "name",
            "value": "Calin",
            "operation":"LIKE"
        },
        {
            "column": "id",
            "value": 2
        }
    ]
}
</pre>
Or query it using GET ( take that json, encode it and then send the request:

![image](https://github.com/chelceacalin/AdvancedSpecification/assets/76866499/320ab7ec-6e20-4dab-bc61-296495d8da30)








