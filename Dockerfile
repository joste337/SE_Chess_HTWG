FROM hseeberger/scala-sbt
WORKDIR /chess
ADD . /chess
CMD sbt test