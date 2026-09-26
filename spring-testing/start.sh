
BUILD_FOLDER="build/install/spring-testing/bin"

if [[ -d $BUILD_FOLDER ]]
then
  cd $BUILD_FOLDER || exit
  bash stop
  cd ../../../../ || exit
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
else
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
fi

# For JaCoCo Test
# gradle clean test
# JaCoCo Repost :-  build/reports/jacoco/test/html/index.html