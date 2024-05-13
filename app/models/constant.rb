module Constant
  module Error
    ERR_001_LOGIN_NAME = "「アカウント名」を入力してください。"
    ERR_001_PASSWORD = "「パスワード」を入力してください。"
    ERR_002 = "画面項目名」を入力してください。"
    ERR_003 = "画面項目名」は既に存在しています。"
    ERR_004 = "画面項目名」は存在していません。"
    ERR_005 = "画面項目名」をxxx形式で入力してください。"
    ERR_006 = "xxxx桁以内の「画面項目名」を入力してください。"
    ERR_007 = "画面項目名」をxxx＜＝桁数、＜＝xxx桁で入力してください。"
    ERR_008 = "画面項目名」に半角英数を入力してください。"
    ERR_009 = "画面項目名」をカタカナで入力してください。"
    ERR_0010 = "画面項目名」をひらがなで入力してください"
    ERR_0011 = "画面項目名」は無効になっています。"
    ERR_0012 = "失効日」は「交付年月日」より未来の日で入力してください。"
    ERR_0013 = "該当するユーザは存在していません。"
    ERR_0014 = "該当するユーザは存在していません。"
    ERR_0015 = "システムエラーが発生しました。"
    ERR_0016 = "「アカウント名」または「パスワード」は不正です。"
    ERR_0017 = "パスワード（確認」が不正です。"
    ERR_0018 = "画面上の項目名」は半角で入力してください。"
    ERR_0019 = "アカウント名]は(a-z, A-Z, 0-9 と _)の桁のみです。最初の桁は数字ではない。"

  end
  module Msg
    MSG001 = "ユーザの登録が完了しました。"
    MSG002 = "ユーザの更新が完了しました。"
    MSG003 = "ユーザの削除が完了しました。"
    MSG004 = "削除しますが、よろしいでしょうか。"
    MSG005 = "検索条件に該当するユーザが見つかりません。"
  end

  module Paging
    RECORD_PER_PAGE = 5
    PAGE_SIZE = 3
  end
end
