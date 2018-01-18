# Marvin

Marvin is a NLP Bot / Helpdesk

## pre-reqs

```sh
conda create -n py36-marvin python=3.6
conda activate py36-marvin
pip install rasa_nlu scipy duckling 
pip install git+https://github.com/mit-nlp/MITIE.git
pip install -U sklearn-crfsuite
pip install spacy scikit-learn
```

## Training base model and running rasa

```sh
python -m rasa_nlu.train -c  config_spacy.json --fixed_model_name=fallback
python -m rasa_nlu.server -c config_spacy.json
```

## entity extraction and ducks

```json
    "entities": [
        {
            "start": 8,
            "end": 15,
            "value": "chinese",
            "entity": "cuisine",
            "extractor": "ner_crf",
            "processors": [
                "ner_synonyms"
            ]
        }
    ],
    
    "entities": [
        {
            "start": 33,
            "end": 34,
            "text": "8",
            "value": 8.0,
            "additional_info": {
                "value": 8.0
            },
            "entity": "number",
            "extractor": "ner_duckling"
        },
        {
            "start": 33,
            "end": 34,
            "text": "8",
            "value": 8.0,
            "additional_info": {
                "value": 8.0,
                "unit": null
            },
            "entity": "distance",
            "extractor": "ner_duckling"
        },
        {
            "start": 33,
            "end": 34,
            "text": "8",
            "value": 8.0,
            "additional_info": {
                "value": 8.0,
                "unit": null,
                "latent": true
            },
            "entity": "volume",
            "extractor": "ner_duckling"
        },
        {
            "start": 33,
            "end": 34,
            "text": "8",
            "value": 8.0,
            "additional_info": {
                "value": 8.0,
                "unit": null
            },
            "entity": "temperature",
            "extractor": "ner_duckling"
        },
        {
            "start": 33,
            "end": 36,
            "text": "8pm",
            "value": "2018-01-17T20:00:00.000Z",
            "additional_info": {
                "value": "2018-01-17T20:00:00.000Z",
                "grain": "hour",
                "others": [
                    {
                        "grain": "hour",
                        "value": "2018-01-17T20:00:00.000Z"
                    },
                    {
                        "grain": "hour",
                        "value": "2018-01-18T20:00:00.000Z"
                    },
                    {
                        "grain": "hour",
                        "value": "2018-01-19T20:00:00.000Z"
                    }
                ]
            },
            "entity": "time",
            "extractor": "ner_duckling"
        },
        {
            "start": 8,
            "end": 15,
            "value": "chinese",
            "entity": "cuisine",
            "extractor": "ner_crf",
            "processors": [
                "ner_synonyms"
            ]
        }
    
    
```


## features
* Vert.x 3.4.1
* Modular Boot Class
* Logback logging framework

## boot class
`Boot.java` reads a specified *conf.json* file and starts up the classes as desribed in the config. each verticle has its own configuration's within the *conf.json*. example:

```json
{
  "conf_source": "this is the root config file",
  "vertx_options": {
    "blocked_thread_check_period": 1000,
    "max_event_loop_execute_time": 2000000000,
    "max_worker_execute_time": 60000000000,
    "quorum_size": 1,
    "ha_group": "__DEFAULT__",
    "ha_enabled": false,
    "metrics_enabled": false
  },
  "services": ["com.deblox.myproject.PingVerticle"],
  "com.deblox.myproject.PingVerticle": {
    "config": {
      "foo": "bar",
      "baz": {}
    },
    "worker": false,
    "multiThreaded": false,
    "isolationGroup": null,
    "ha": false,
    "extraClasspath": null,
    "instances": 1,
    "redeploy": true,
    "redeployScanPeriod": 250,
    "redeployGracePeriod": 1000
  }
}
```

## idea
to generate the idea files

```sh
./gradlew idea
```

## testing

### all tests
```sh
./gradlew test -i
```

### single test

```sh
gradle test --tests "org.gradle.SomeTest.someSpecificFeature"
gradle test --tests "*SomeTest.someSpecificFeature"
gradle test --tests "*SomeSpecificTest"
gradle test --tests "all.in.specific.package*"
gradle test --tests "*IntegTest"
gradle test --tests "*IntegTest*ui*"
```

## running from Idea
the Boot.java class can be run directly and accepts `-conf` argument for specifiying config json.


## building fatJar
the gradle task *shadowJar* will build a executable jar.

```
./gradlew shadowJar
```

## running
when running as a fatJar, remember to specify the alternate logging implementation.


```
JAVA_OPTS="-Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.SLF4JLogDelegateFactory"
java $JAVA_OPTS -jar my-module-1.0.0-final-fat.jar -cp /dir/with/logback/xml
```

