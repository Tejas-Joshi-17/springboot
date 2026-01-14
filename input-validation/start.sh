
BUILD_FOLDER="build/install/input-validation/bin"

if [[ -d $BUILD_FOLDER ]]
then
  cd $BUILD_FOLDER || exit
  bash stop
  cd ../../../../ || exit
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash start
else
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash start
fi