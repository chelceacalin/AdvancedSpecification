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

![image](https://github.com/chelceacalin/AdvancedSpecification/assets/76866499/142e6d3c-8306-4f4a-9de2-947ea9ef1654)







