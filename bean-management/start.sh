
BUILD_FOLDER="build/install/bean-management/bin"
if [[ -d $BUILD_FOLDER ]]
then
  cd $BUILD_FOLDER || exit
  bash stop
  cd ../../../../ || exit
  gradle clean iA && {
    echo "jar generated successfully !!!!"
  }

#  cd $BUILD_FOLDER || exit
#  bash start && {
#    cd ../logs || exit
#    less namco-big.log
#  }
else
    gradle clean iA && {
      echo "jar generated successfully !!!!"
    }

#    cd $BUILD_FOLDER || exit
#    bash start && {
#      cd ../logs || exit
#      less namco-big.log
#    }
fi

#
#PID=$(ps -ef | grep "bean-management" | grep -v grep | awk '{print $2}');
#if [[ -n $PID ]]
#then
#    kill -9 $PID
#    echo "Killed Already Running Process"
#fi